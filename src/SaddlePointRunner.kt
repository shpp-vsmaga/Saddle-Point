fun main(args: Array<String>) {

    for (matrix in testMatrices) {
        printTestMatrix(matrix)
        if (matrixIsCorrect(matrix)) {
            val minMaxAndMaxMin: Pair<List<Point>, List<Point>> = findSaddlePoints(matrix)
            printSaddlePoints(minMaxAndMaxMin)
        } else {
            println("Matrix is wrong")
            println()
        }
    }
}

private fun printTestMatrix(matrix: Array<IntArray>) {
    println("Matrix:")
    for (column in matrix) {
        println(column.joinToString(" "))
    }
}


/**
 * @return true if matrix is not empty and length of all rows are equals
 */
private fun matrixIsCorrect(matrix: Array<IntArray>): Boolean {
    if (matrix.isEmpty()) {
        return false
    }
    val rowSize = matrix[0].size
    for (currentRow in matrix) {
        if (currentRow.size != rowSize) {
            return false
        }
    }

    return true
}

private fun printSaddlePoints(pair: Pair<List<Point>, List<Point>>) {
    if (pair.first.isEmpty() && pair.second.isEmpty()) {
        println("Saddle points are absent")
        println()
        return
    }

    println("Saddle points:")
    if (pair.first.isNotEmpty()){
        println("Minimal in row and maximal in column points count ${pair.first.size}: ${pair.first}")
    }

    if (pair.second.isNotEmpty()){
        println("Maximal in row and minimal in column points count ${pair.second.size}: ${pair.second}")
    }
    println()
}


/**
 * Find saddle elements in 2D int array - elements that are minimal in their rows
 * and maximal in their columns
 * or
 * maximal in their rows and minimal in their columns
 *
 * @param matrix - 2D int array
 *
 * @return pair of lists of numbers with their coordinates, where
 * first - list of numbers that are minimal in the row and maximal in the column
 * second -  list of numbers that are maximal in the row and minimal in the column
 */
private fun findSaddlePoints(matrix: Array<IntArray>): Pair<List<Point>, List<Point>> {
    val minMaxList = ArrayList<Point>()
    val maxMinList = ArrayList<Point>()

    for (rowIndex in 0 until matrix.size) {
        var minInRow = matrix[rowIndex].min() //find minimal number in the row

        for (columnIndex in 0 until matrix[rowIndex].size) {

            // iterate over all minimal numbers in row
            // and check is it maximal in column
            if (matrix[rowIndex][columnIndex] == minInRow) {
                minInRow = matrix[rowIndex][columnIndex]
                if (isMaxInColumn(minInRow, columnIndex, matrix)) {
                    minMaxList.add(Point(minInRow, rowIndex, columnIndex))
                }
            }
        }

        var maxInRow = matrix[rowIndex].max() //find maximal number in the row

        for (columnIndex in 0 until matrix[rowIndex].size) {

            // iterate over all maximal numbers in row
            // and check is it minimal in column
            if (matrix[rowIndex][columnIndex] == maxInRow) {
                maxInRow = matrix[rowIndex][columnIndex]
                if (isMinInColumn(maxInRow, columnIndex, matrix)) {
                    maxMinList.add(Point(maxInRow, rowIndex, columnIndex))
                }
            }
        }
    }
    return Pair(minMaxList, maxMinList)
}


/**
 * Check is number minimal in the column of 2D array
 * @param number  - number to check
 * @param columnIndex - index of column in 2D array
 * @param matrix - 2D Int array where need to check
 *
 * @return true if number is minimal in corresponding column
 */
private fun isMinInColumn(number: Int?, columnIndex: Int, matrix: Array<IntArray>): Boolean {
    var min: Int = matrix[0][columnIndex]
    for (rowIndex in 0 until matrix.size) {

        if (matrix[rowIndex][columnIndex] < min) {
            min = matrix[rowIndex][columnIndex]
        }
    }
    return number == min
}


/**
 * Check is number maximal in the column of 2D array
 * @param number  - number to check
 * @param columnIndex - index of column in 2D array
 * @param matrix - 2D Int array where need to check
 *
 * @return true if number is maximal in corresponding column
 */
private fun isMaxInColumn(number: Int?, columnIndex: Int, matrix: Array<IntArray>): Boolean {
    var max: Int = matrix[0][columnIndex]
    for (rowIndex in 0 until matrix.size) {

        if (matrix[rowIndex][columnIndex] > max) {
            max = matrix[rowIndex][columnIndex]
        }
    }
    return number == max
}