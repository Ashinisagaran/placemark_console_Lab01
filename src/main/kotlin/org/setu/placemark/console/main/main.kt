package org.setu.placemark.console.main

//import mu.KotlinLogging
import org.setu.placemark.console.controllers.PlacemarkController
//import org.setu.placemark.console.models.PlacemarkMemStore
//import org.setu.placemark.console.models.PlacemarkModel
//import org.setu.placemark.console.views.PlacemarkView

val controller = PlacemarkController()

fun main(args: Array<String>) {

   controller.start()
}
