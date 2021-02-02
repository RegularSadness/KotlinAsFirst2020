package additionalTask

import java.io.File
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.ArrayList

data class Coordinate(val x: Int, val y: Int) {
    constructor(coordinate: Coordinate, x: Int, y: Int) :
            this(coordinate.x + x, coordinate.y + y)

    override fun toString(): String = "($x, $y)"
}

fun main(fileName: String, limit: Int): MutableList<Coordinate> {

    val file = File(fileName)
    val lineCount = 9

    val pawnsCoordinates = getCoordinates(file, lineCount, 'p')
    if (pawnsCoordinates.size < 1) {
        throw IllegalArgumentException("File should contains > 1 \'p\' char")
    }
    val horseCoordinates = getCoordinates(file, lineCount, 'N')
    if (horseCoordinates.size != 1) {
        throw IllegalArgumentException("File should contains 1 \'N\' char")
    }

    val horseCoordinate = horseCoordinates[0]

    val findPath = findPath(horseCoordinate, 0, pawnsCoordinates, mutableListOf(), limit)
    if (findPath.isEmpty()) {
        val message = String.format("The number of moves exceeds the limit %s", limit)
        throw RuntimeException(message)
    }
    return findPath
}

fun getCoordinates(file: File, lineCount: Int, char: Char): MutableList<Coordinate> {
    val pawnsCoordinates = mutableListOf<Coordinate>()
    var lineNumber = lineCount
    file.forEachLine {
        lineNumber--
        var pawnIndex = 0
        do {
            pawnIndex = it.indexOf(char, pawnIndex)
            if (pawnIndex != -1) {
                pawnsCoordinates.add(Coordinate(pawnIndex + 1, lineNumber))
            }
            pawnIndex++
        } while (pawnIndex > 0)
    }
    return pawnsCoordinates
}

fun findPath(
    coordinate: Coordinate,
    iteration: Int,
    pawns: List<Coordinate>,
    path: MutableList<Coordinate>,
    limit: Int
): MutableList<Coordinate> {

    var iteration = iteration
    if (iteration >= limit) {
        return mutableListOf()
    }
    iteration++

    val possibleNextCoordinates = mutableListOf<Coordinate>()
    possibleNextCoordinates.add(moveLeftAndDown(coordinate))
    possibleNextCoordinates.add(moveLeftAndUp(coordinate))
    possibleNextCoordinates.add(moveUpAndLeft(coordinate))
    possibleNextCoordinates.add(moveUpAndRight(coordinate))
    possibleNextCoordinates.add(moveRightAndUp(coordinate))
    possibleNextCoordinates.add(moveRightAndDown(coordinate))
    possibleNextCoordinates.add(moveDownAndRight(coordinate))
    possibleNextCoordinates.add(moveDownAndLeft(coordinate))

    for (possibleNextCoordinate in possibleNextCoordinates) {
        val resultPath = checkRecursive(copyList(pawns), possibleNextCoordinate, path, iteration, limit)
        if (resultPath.isNotEmpty()) {
            return resultPath
        }
    }

    return mutableListOf()
}

fun checkRecursive(
    pawns: MutableList<Coordinate>,
    coordinate: Coordinate,
    path: MutableList<Coordinate>,
    iteration: Int,
    limit: Int
): MutableList<Coordinate> {
    if (!isValid(coordinate)) {
        return mutableListOf()
    }
    pawns.remove(coordinate)
    return if (pawns.isEmpty() || path.isNotEmpty()) {
        path.add(coordinate)
        path
    } else {
        val resultPath = findPath(coordinate, iteration, pawns, path, limit)
        if (resultPath.isNotEmpty()) {
            resultPath.add(coordinate)
            resultPath
        } else {
            mutableListOf()
        }
    }
}

fun isValid(coordinate: Coordinate): Boolean =
    !(coordinate.x < 1 || coordinate.y < 1 || coordinate.x > 8 || coordinate.y > 8)

fun copyList(coordinates: List<Coordinate>): MutableList<Coordinate> {
    val coordinatesCopy: MutableList<Coordinate> = ArrayList()
    coordinatesCopy.addAll(coordinates)
    return coordinatesCopy
}

fun moveLeftAndDown(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -2, -1)

fun moveLeftAndUp(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -2, 1)

fun moveDownAndLeft(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -1, -2)

fun moveUpAndLeft(coordinate: Coordinate): Coordinate = Coordinate(coordinate, -1, 2)

fun moveDownAndRight(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 1, -2)

fun moveUpAndRight(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 1, 2)

fun moveRightAndDown(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 2, -1)

fun moveRightAndUp(coordinate: Coordinate): Coordinate = Coordinate(coordinate, 2, 1)



