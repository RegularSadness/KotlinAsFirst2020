@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import java.lang.IllegalArgumentException

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

//    operator fun get(value: E): Cell

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)

fun <E> createCopyOfMatrix(matrix: Matrix<E>): Matrix<E> {
    val newMatrix = MatrixImpl(matrix.height, matrix.width, matrix[0, 0])
    for (i in 0 until matrix.height) {
        for (j in 0 until matrix.width) {
            val tempValue = matrix[j, i]
            newMatrix[j, i] = tempValue
        }
    }

    return newMatrix
}

fun <E> createMatrixWithValues(height: Int, width: Int, values: List<E>): Matrix<E> {
    val newMatrix = MatrixImpl(height, width, values[0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            newMatrix[i, j] = values[width * i + j]
        }
    }
    return newMatrix
}


/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    public var list = mutableListOf<E>() //TODO: do final and change in new object

    init {
        val elementsCount = width * height
        for (i in 0 until elementsCount) {
            list.add(e)
        }
    }

    override fun get(row: Int, column: Int): E {
        if (row > height - 1 && column > width - 1 && row <= 0 && column <= 0) {
            throw IllegalArgumentException("The element does not exist")
        }
        var elementIndex = width * row + column
        return list[elementIndex]

    }

    override fun get(cell: Cell): E = get(cell.row, cell.column)

//    override fun get(value: E): Cell {
//        var elementIndex = list.indexOf(value)
//        var row = elementIndex / width
//        var column = elementIndex % height
//        return Cell(row, column)  //TODO what if more then 1 element of value
//    }

    override fun set(row: Int, column: Int, value: E) {
        if (row > height - 1 && column > width - 1 && row <= 0 && column <= 0) {
            throw IllegalArgumentException("The element does not exist")
        }
        var elementIndex = width * row + column
        list[elementIndex] = value
    }

    override fun set(cell: Cell, value: E) {
        return set(
            cell.row,
            cell.column,
            value
        )
    }

    override fun equals(other: Any?) =
        other is MatrixImpl<*> && height == other.height && width == other.width && list == other.list

    override fun toString(): String {
        return buildString {
            for (row in 0 until height) {
                append("[ ")
                for (column in 0 until width) {
                    append(get(row, column))
                    append(" ")
                }
                append("]")
                append(System.lineSeparator())
            }
        }
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + list.hashCode()
        return result
    }
}

