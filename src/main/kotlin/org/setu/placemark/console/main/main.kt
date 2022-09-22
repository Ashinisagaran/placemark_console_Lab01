package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}

var placemark = PlacemarkModel()
val placemarks = ArrayList<PlacemarkModel>()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listPlacemark()
            4 -> searchPlacemark()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

//fun addPlacemark(){
//    println("Add Placemark")
//    println()
//    print("Enter a Title : ")
//    placemark.title = readLine()!!
//    print("Enter a Description : ")
//    placemark.description = readLine()!!
//
//    if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
//        placemarks.add(placemark.copy())
//        logger.info("Placemark Added : [ $placemark ]")
//    }
//    else
//        logger.info("Placemark Not Added")
//
//    placemark.id++
//}

fun addPlacemark(){
    var aPlacemark = PlacemarkModel()
    println("Add Placemark")
    println()
    print("Enter a Title : ")
    aPlacemark.title = readLine()!!
    print("Enter a Description : ")
    aPlacemark.description = readLine()!!

    if (aPlacemark.title.isNotEmpty() && aPlacemark.description.isNotEmpty())
    {
        aPlacemark.id = placemarks.size.toLong()
        placemarks.add(aPlacemark.copy())
        logger.info("Placemark Added : [ $aPlacemark ]")
    }
    else
        logger.info("Placemark Not Added")
}

//fun updatePlacemark(){
//
//    println("Update Placemark")
//    println()
//    print("Enter a new Title for [ " + placemark.title + "] : ")
//    placemark.title = readLine()!!
//    print("Enter a new Description for [ " + placemark.description + "] : ")
//    placemark.description = readLine()!!
//    println("You updated [ " + placemark.title + "] for title and [ " + placemark.description + "] for description")
//}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listPlacemark()
    var searchId = getId()
    val aPlacemark = search(searchId)
    var title: String
    var desc: String


    if(aPlacemark != null) {


            print("Enter a new Title for [ ${aPlacemark.title} ] : ")
            title = readLine()!!
            print("Enter a new Description for [ ${aPlacemark.description} ] : ")
            desc = readLine()!!
            println(
                "You updated [ $title ] for title " +
                        "and [ $desc ] for description"
            )
        if (title.isNotEmpty() && desc.isNotEmpty())
        {
            aPlacemark.title=title
            aPlacemark.description=desc
        } else
            println("Placemark Not Updated...")

    }
    else
        println("Placemark Not Found..")
}


fun listPlacemark() {
    println("List All Placemarks")
    println()
    placemarks.forEach { logger.info("${it}") }
}

fun searchPlacemark() {

    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
}


fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}



