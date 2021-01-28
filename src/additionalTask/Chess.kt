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
    if (pawnsCoordinates.size != 3) {
        throw IllegalArgumentException("File should contains 3 \'p\' char")
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
    val leftAndDownCoordinate = moveLeftAndDown(coordinate)
    val resultPath = checkRecursive(copyList(pawns), leftAndDownCoordinate, path, iteration, limit)
    if (resultPath.isNotEmpty()) {
        return resultPath
    }
    val leftAndUpCoordinate = moveLeftAndUp(coordinate)
    val resultPath1 = checkRecursive(copyList(pawns), leftAndUpCoordinate, path, iteration, limit)
    if (resultPath1.isNotEmpty()) {
        return resultPath1
    }
    val upAndLeftCoordinate = moveUpAndLeft(coordinate)
    val resultPath2 = checkRecursive(copyList(pawns), upAndLeftCoordinate, path, iteration, limit)
    if (resultPath2.isNotEmpty()) {
        return resultPath2
    }
    val upAndRightCoordinate = moveUpAndRight(coordinate)
    val resultPath3 = checkRecursive(copyList(pawns), upAndRightCoordinate, path, iteration, limit)
    if (resultPath3.isNotEmpty()) {
        return resultPath3
    }
    val rightAndUpCoordinate = moveRightAndUp(coordinate)
    val resultPath4 = checkRecursive(copyList(pawns), rightAndUpCoordinate, path, iteration, limit)
    if (resultPath4.isNotEmpty()) {
        return resultPath4
    }
    val rightAndDownCoordinate = moveRightAndDown(coordinate)
    val resultPath5 = checkRecursive(copyList(pawns), rightAndDownCoordinate, path, iteration, limit)
    if (resultPath5.isNotEmpty()) {
        return resultPath5
    }
    val downAndRightCoordinate = moveDownAndRight(coordinate)
    val resultPath6 = checkRecursive(copyList(pawns), downAndRightCoordinate, path, iteration, limit)
    if (resultPath6.isNotEmpty()) {
        return resultPath6
    }
    val downAndLeftCoordinate = moveDownAndLeft(coordinate)
    val resultPath7 = checkRecursive(copyList(pawns), downAndLeftCoordinate, path, iteration, limit)
    return if (resultPath7.isNotEmpty()) {
        resultPath7
    } else mutableListOf()
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



