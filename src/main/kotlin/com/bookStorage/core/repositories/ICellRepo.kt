package com.bookStorage.core.repositories

import com.bookStorage.core.models.CellModel
import java.util.UUID

interface ICellRepo {

    fun CheckIfEmptyCells(id: Int): Boolean
    fun ClearCell(id: Int): Boolean // Если запись книги удалили из бд например
    fun MoveFromCellToCell(firstId: Int, SecId: Int): Boolean // перемещение книги из одной ячейки в другую
    fun GetCellById(id: Int): CellModel
}