package com.bookStorage.infrastucture.repositories

import com.bookStorage.core.models.BookModel
import com.bookStorage.core.models.CellModel
import com.bookStorage.core.repositories.ICellRepo
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CellRepo(val sessionFactory: SessionFactory): ICellRepo {

    // false - пустая, true - не пустая
    override fun CheckIfEmptyCells(id: Int): Boolean {
        val session: Session = sessionFactory.openSession()
        val cb: CriteriaBuilder = session.criteriaBuilder
        val cq: CriteriaQuery<CellModel> = cb.createQuery(CellModel::class.java)
        val root: Root<CellModel> = cq.from(CellModel::class.java)

        cq.select(root)
        cq.where(cb.equal(root.get<Int>("id"), id))

        try {
            val result = session.createQuery(cq).uniqueResult()
            if(result.bookId.id == UUID(0,0)){
                return  false
            }
        }
        catch (e: Exception){
            return false
        }



        session.close()
        return true
    }

    override fun ClearCell(id: Int): Boolean {
        val session: Session = sessionFactory.openSession()
        val transaction = session.transaction

        var result: Boolean = false

        val cb: CriteriaBuilder = session.criteriaBuilder

        val cu = cb.createCriteriaUpdate(CellModel::class.java)
        val root = cu.from(CellModel::class.java)

        cu.where(cb.equal(root.get<Int>("id"), id))
        cu.set(root.get<BookModel>("bookId"), cb.nullLiteral(BookModel::class.java))

        try {
            transaction.begin()
            transaction.commit()
            result = true
        }
        catch (e: HibernateException){
            transaction.rollback()
            e.printStackTrace()
        }
        catch (e: Exception){
            transaction.rollback()
            e.printStackTrace()
        }

        session.close()

        return result
    }

    // чтоб передвинуть из ячейки в ячейку, надо сначала получить модель ячейки из которой передвигается
    override fun MoveFromCellToCell(firstId: Int, secId: Int): Boolean {
        val session: Session = sessionFactory.openSession()
        val transaction = session.transaction

        var result: Boolean = false

        val cb: CriteriaBuilder = session.criteriaBuilder

        val cuOne = cb.createCriteriaUpdate(CellModel::class.java)// для firstId
        // А где лучше сделать проверку на то есть ли что двигать из ячейки-то? просто это можно сделать тут, чтобы
        // нагрузка на бд была меньше или можно сделать в сервисе на ряду с проверкой ячейки 2
        val cqOne = cb.createQuery(CellModel::class.java)
        val root = cuOne.from(CellModel::class.java)

        // проверка на то есть ли в ячейке номер два привязанные книги должна быть реализована в сервисе
        val cuTwo = cb.createCriteriaUpdate(CellModel::class.java)// для secId


        // прописываени udate первой ячейки
        cuOne.where(cb.equal(root.get<Int>("id"),firstId))

        // прописывание получения первой ячейки
        cqOne.select(root).where(cb.equal(root.get<Int>("id"), firstId))

        try {
            transaction.begin()
            var firstCell:CellModel = session.createQuery(cqOne).uniqueResult()

            // прописывание update второй ячейки
            cuTwo.where(cb.equal(root.get<Int>("id"), secId))
            cuTwo.set(root.get<BookModel>("bookId"), firstCell.bookId)

            session.createMutationQuery(cuTwo).executeUpdate()
            session.createMutationQuery(cuOne).executeUpdate()
            transaction.commit()
            session.flush()
            result = true
        }
        catch (e: HibernateException){
            transaction.rollback()
            e.printStackTrace()
        }
        catch (e: Exception){
            transaction.rollback()
            e.printStackTrace()
        }

        session.close()

        return result
    }

    override fun GetCellById(id: Int): CellModel {
        val session = sessionFactory.openSession()

        val cb: CriteriaBuilder = session.criteriaBuilder

        val cq: CriteriaQuery<CellModel> = cb.createQuery(CellModel::class.java)
        val root = cq.from(CellModel::class.java)

        cq.select(root).where(cb.equal(root.get<Int>("id"), id))

        var result = session.createQuery(cq).uniqueResult()

        session.close()

        return result
    }
}