package com.bookStorage.infrastucture.repositories

import com.bookStorage.core.models.BookModel
import com.bookStorage.core.models.CellModel
import com.bookStorage.core.models.GenreModel
import com.bookStorage.core.repositories.IBookRepo
import jakarta.persistence.criteria.CriteriaQuery

import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class BookRepo(val sessionFactory: SessionFactory): IBookRepo {

    override fun CreateBookModel(bookModel: BookModel): Boolean {
        val session: Session = sessionFactory.openSession()
        val transaction: Transaction = session.transaction

        val criteriaBuilder = session.criteriaBuilder

        val getFirstEmptyCell: CriteriaQuery<CellModel> = criteriaBuilder.createQuery(CellModel::class.java)
        val subroot = getFirstEmptyCell.from(CellModel::class.java)

        getFirstEmptyCell.where(subroot.get<Boolean?>("bookId").isNull)

        val cellQuery: Query<CellModel> = session.createQuery(getFirstEmptyCell).setMaxResults(1).setFirstResult(0)


        var result: Boolean = false

        try {
            transaction.begin()
            session.persist(bookModel)

            val cell: CellModel = cellQuery.singleResult

            cell.bookId = bookModel

            session.merge(cell)
            transaction.commit()
            result = true
        }
        catch (e: HibernateException){
            e.printStackTrace()
            transaction.rollback()
        }
        catch (e: Exception){
            e.printStackTrace()
            transaction.rollback()
        }
        finally {
            session.close()
        }

        return result
    }

    override fun UpdateBookModel(bookModel: BookModel): Boolean {
        val session: Session = sessionFactory.openSession()
        val transaction: Transaction = session.transaction

        var result: Boolean = false

        try {
            transaction.begin()

            transaction.commit()
            result = true
        }
        catch (e: HibernateException){
            e.printStackTrace()
            transaction.rollback()
        }
        catch (e: Exception){
            e.printStackTrace()
            transaction.rollback()
        }
        finally {
            session.close()
        }

        return result
    }

    override fun DeleteBookModel(id: UUID): Boolean{
        val session: Session = sessionFactory.openSession()
        val transaction: Transaction = session.transaction

        var result: Boolean = false

        try {
            transaction.begin()

            transaction.commit()
            result = true
        }
        catch (e: HibernateException){
            e.printStackTrace()
            transaction.rollback()
        }
        catch (e: Exception){
            e.printStackTrace()
            transaction.rollback()
        }
        finally {
            session.close()
        }

        return result
    }


    override fun GetAllBooksByPage(bookPage: Int, pageSize: Int): List<BookModel> {
        TODO("Not yet implemented")
    }

    override fun GetBookById(id: UUID): BookModel {
        TODO("Not yet implemented")
    }

    override fun GetBooksByAuthorSearch(name: String) {
        TODO("Not yet implemented")
    }

    override fun GetBooksByAuthorByPage(name: String, page: Int, pageSize: Int) {
        TODO("Not yet implemented")
    }

    override fun GetBooksByGenre(genre: GenreModel): List<BookModel> {
        TODO("Not yet implemented")
    }

    override fun GetBookByName(name: String): List<BookModel> {
        TODO("Not yet implemented")
    }
}