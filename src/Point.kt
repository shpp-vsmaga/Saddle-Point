data class Point(
        val number: Int,
        val rowIndex: Int,
        val colIndex: Int
) {
    override fun toString(): String {
        return "{$number - (row $rowIndex, col $colIndex)}"
    }
}