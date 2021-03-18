package additionalTask

import java.io.File
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.ArrayList
import kotlin.math.max

//data class Coordinate(val x: Int, val y: Int) {
//    constructor(coordinate: Coordinate, x: Int, y: Int) :
//            this(coordinate.x + x, coordinate.y + y)
//
//    override fun toString(): String = "($x, $y)"
//}
//
//fun main(fileName: String, limit: Int): MutableList<Coordinate> = TODO()
//
//    val file = File(fileName)
//    val lineCount = 9
//
//    val pawnsCoordinates = getCoordinates(file, lineCount, 'p')
//    if (pawnsCoordinates.size < 1) {
//        throw IllegalArgumentException("File should contains > 1 \'p\' char")
//    }
//    val horseCoordinates = getCoordinates(file, lineCount, 'N')
//    if (horseCoordinates.size != 1) {
//        throw IllegalArgumentException("File should contains 1 \'N\' char")
//    }
//
//    val horseCoordinate = horseCoordinates[0]
//
//    val findPath = findPath(horseCoordinate, 0, pawnsCoordinates, mutableListOf(), limit)
//    if (findPath.isEmpty()) {
//        val message = String.format("The number of moves exceeds the limit %s", limit)
//        throw RuntimeException(message)
//    }
//
//    findPath.reverse()
//    val outputFile = File("input/ChessOutput.txt")
//    var resultOutput = ""
//    var currentLine = 9
//    file.forEachLine {
//        currentLine--
//        var lineForWriting = it
//        for (i in 1..8) {
//            val currentCoordinate = Coordinate(i, currentLine)
//            if (findPath.contains(currentCoordinate)) {
//                val coordinateIndex = findPath.indexOf(currentCoordinate)
//                lineForWriting = lineForWriting.substring(0, i - 1) + coordinateIndex + lineForWriting.substring(
//                    i,
//                    lineForWriting.length
//                )
//            }
//        }
//        resultOutput = resultOutput + lineForWriting + System.lineSeparator()
//        //println(lineForWriting)
//    }
//    outputFile.writeText(resultOutput)
//    return findPath
//}
//
//fun getCoordinates(file: File, lineCount: Int, char: Char): MutableList<Coordinate> {
//    val pawnsCoordinates = mutableListOf<Coordinate>()
//    var lineNumber = lineCount
//    file.forEachLine {
//        lineNumber--
//        var pawnIndex = 0
//        do {
//            pawnIndex = it.indexOf(char, pawnIndex)
//            if (pawnIndex != -1) {
//                pawnsCoordinates.add(Coordinate(pawnIndex + 1, lineNumber))
//            }
//            pawnIndex++
//        } while (pawnIndex > 0)
//    }
//    return pawnsCoordinates
//}
//
//fun findPath(
//    coordinate: Coordinate,
//    iteration: Int,
//    pawns: List<Coordinate>,
//    path: MutableList<Coordinate>,
//    limit: Int
//): MutableList<Coordinate> {
//
//    var iteration = iteration
//    if (iteration >= limit) {
//        return mutableListOf()
//    }
//    iteration++
//
//    val possibleNextCoordinates = mutableListOf<Coordinate>()
//    possibleNextCoordinates.add(moveLeftAndDown(coordinate))
//    possibleNextCoordinates.add(moveLeftAndUp(coordinate))
//    possibleNextCoordinates.add(moveUpAndLeft(coordinate))
//    possibleNextCoordinates.add(moveUpAndRight(coordinate))
//    possibleNextCoordinates.add(moveRightAndUp(coordinate))
//    possibleNextCoordinates.add(moveRightAndDown(coordinate))
//    possibleNextCoordinates.add(moveDownAndRight(coordinate))
//    possibleNextCoordinates.add(moveDownAndLeft(coordinate))
//
//    for (possibleNextCoordinate in possibleNextCoordinates) {
//        val resultPath = checkRecursive(copyList(pawns), possibleNextCoordinate, path, iteration, limit)
//        if (resultPath.isNotEmpty()) {
//            return resultPath
//        }
//    }
//
//    return mutableListOf()
//}
//
//fun checkRecursive(
//    pawns: MutableList<Coordinate>,
//    coordinate: Coordinate,
//    path: MutableList<Coordinate>,
//    iteration: Int,
//    limit: Int
//): MutableList<Coordinate> {
////    println(coordinate)
//    if (!isValid(coordinate)) {
////        println("out of field")
//        return mutableListOf()
//    }
//    pawns.remove(coordinate)
//    return if (pawns.isEmpty() || path.isNotEmpty()) {
////        println("all found")
////        println(coordinate)
//        path.add(coordinate)
//        path
//    } else {
//        val resultPath = findPath(coordinate, iteration, pawns, path, limit)
//        if (resultPath.isNotEmpty()) {
////            println("get back")
////            println(coordinate)
//            resultPath.add(coordinate)
//            resultPath
//        } else {
//            mutableListOf()
//        }
//    }
//}
//
//fun isValid(coordinate: Coordinate): Boolean =
//    !(coordinate.x < 1 || coordinate.y < 1 || coordinate.x > 8 || coordinate.y > 8)
//
//fun copyList(coordinates: List<Coordinate>): MutableList<Coordinate> {
//    val coordinatesCopy: MutableList<Coordinate> = ArrayList()
//    coordinatesCopy.addAll(coordinates)
//    return coordinatesCopy
//}
//
//fun moveLeftAndDown(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -2, -1)
//
//fun moveLeftAndUp(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -2, 1)
//
//fun moveDownAndLeft(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -1, -2)
//
//fun moveUpAndLeft(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -1, 2)
//
//fun moveDownAndRight(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 1, -2)
//
//fun moveUpAndRight(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 1, 2)
//
//fun moveRightAndDown(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 2, -1)
//
//fun moveRightAndUp(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 2, 1)

fun nameByPhone(index: Int): List<String> {
    var nameIndex = mapOf<Int, List<String>>(4826 to listOf("Ivan", "Hubo"), 7387 to listOf("Petr"), 62946 to listOf("Maxim"))

    var resultNames = nameIndex.get(index)
    if (resultNames == null) {
        throw IllegalArgumentException()
    } else {
        return resultNames
    }
}

fun charSequence(chipCoords: String) {
    var chipCoordsTest: String = "42 44 57 67 77 53 33 23 42 78"
    var coordinates = chipCoords.split(" ").toMutableList()
    var result = mutableListOf<String>()
    var formatedString = mutableListOf<Int>()
    var maxLength = 0
    println(coordinates)

    for (i in 0..coordinates.size - 1) {
        var firstCoordinate = coordinates[i].toInt()
        formatedString.add(firstCoordinate)
        //println(firstCoordinate)
        for (i in 0..coordinates.size - 2) {
            var secondCoordinate = coordinates[i + 1].toInt()
            //println(secondCoordinate + "!")
            if ((firstCoordinate / 10) == (secondCoordinate / 10)) {
                formatedString.add(secondCoordinate)
                if(formatedString.size > maxLength) {
                    maxLength = formatedString.size
                    println(formatedString)
                }
                //println(formatedString)
            }
        }
    }
    //println(result)

}

