package be.tim.fleettracker.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// ResultType: Type for the Resource data.
// RequestType: Type for the API response.
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
protected constructor() {

    private val asObservable: Observable<Resource<ResultType>>

    init {
        val source: Observable<Resource<ResultType>>
        if (shouldFetch()) {

            source = createCall()
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    saveCallResult(processResponse(it)!!) }

                .flatMap {
                    loadFromDb().toObservable()
                        .map { Resource.success(it) } }

                .doOnError { onFetchFailed() }

                .onErrorResumeNext { t : Throwable ->
                    loadFromDb().toObservable().map {
                        Resource.error(t.message!!, it) }
                }

                .observeOn(AndroidSchedulers.mainThread())

        } else {
            source = loadFromDb()
                .toObservable()
                .map { Resource.success(it) }
        }

        asObservable = Observable.concat(
            loadFromDb()
                .toObservable()
                .map { Resource.loading(it) }
                .take(1),
            source
        )
    }

    fun getAsObservable(): Observable<Resource<ResultType>> {
        return asObservable
    }

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    private fun onFetchFailed() {}

    @WorkerThread
    protected fun processResponse(response: Resource<RequestType>): RequestType? {
        return response.data
    }

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract fun shouldFetch(): Boolean

    // Called to get the cached data from the database.
    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall(): Observable<Resource<RequestType>>
}