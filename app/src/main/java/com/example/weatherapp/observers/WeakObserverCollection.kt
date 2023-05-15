package com.example.weatherapp.observers

import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArrayList


@Suppress("UNCHECKED_CAST")
class WeakObserverCollection<O : DisposableObserver<*>?> {

    private val mItems: CopyOnWriteArrayList<WeakReference<O?>?>?
    fun size(): Int {
        return mItems!!.size
    }

    fun add(item: O) {
        removeReleased()
        mItems!!.add(WeakReference(item))
    }

    fun disposeItems() {
        synchronized(mItems!!) {
            for (observerReference in mItems) {
                observerReference!!.get()?.dispose()
            }
            mItems.clear()
        }
    }

    private fun removeReleased() {
        synchronized(mItems!!) {
            val observerIterator: Iterator<*> = mItems.iterator()
            while (observerIterator.hasNext()) {
                val observerReference = observerIterator.next() as WeakReference<O?>
                if (observerReference.get() == null) mItems.remove(observerReference)
            }
        }
    }

    init {
        mItems = CopyOnWriteArrayList<WeakReference<O?>?>()
    }
}