package com.example.placemarks.models

import java.util.concurrent.atomic.AtomicLong

class PlacemarkMemStore {

    private val placemarks = ArrayList<PlacemarkModel>()
    private val lastId = AtomicLong(0L)

     fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

     fun create(placemark: PlacemarkModel) {
        placemark.id = lastId.incrementAndGet()
        placemarks.add(placemark)
    }

     fun update(placemark: PlacemarkModel): Boolean {
        val foundPlacemark = findOne(placemark.id)
        return if (foundPlacemark != null) {
            val foundIndex = 0
            placemarks[foundIndex] = placemarks[foundIndex].copy(
                id = placemark.id,
                title = placemark.title,
                description = placemark.description
            )
            true
        } else {
            false
        }
    }

     fun delete(id: Long): Boolean {
        val foundPlacemark = findOne(id)
        return if (foundPlacemark != null) {
            placemarks.remove(foundPlacemark)
            true
        } else {
            false
        }
    }

     fun findOne(id: Long): PlacemarkModel? {
        return placemarks.find { p -> p.id == id }
    }
}
