package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

private val logger = KotlinLogging.logger {}

val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = placemarkView.menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> placemarkView.listPlacemarks(placemarks)
            4 -> searchPlacemark()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun addPlacemark(){
    var aPlacemark = PlacemarkModel()

    if(placemarkView.addPlacemarkData(aPlacemark))
    {
        placemarks.create(aPlacemark)
    }
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {
    placemarkView.listPlacemarks(placemarks)
    var searchId = placemarkView.getId()
    val aPlacemark = search(searchId)


    if(aPlacemark != null) {
        if (placemarkView.updatePlacemarkData(aPlacemark))
        {
            placemarks.update(aPlacemark)
            placemarkView.showPlacemark(aPlacemark)
            logger.info("Placemark Updated : [ $aPlacemark ]")
        } else
            println("Placemark Not Updated...")

    }
    else
        println("Placemark Not Found..")
}

fun searchPlacemark() {

    val aPlacemark = search(placemarkView.getId())!! //ask about this
    placemarkView.showPlacemark(aPlacemark)
}


fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark = placemarks.findOne(id)
    return foundPlacemark
}

fun dummyData() {
    placemarks.create(PlacemarkModel(title = "New York New York", description = "So Good They Named It Twice"))
    placemarks.create(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.create(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}



