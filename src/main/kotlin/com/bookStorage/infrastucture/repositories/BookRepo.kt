package com.bookStorage.infrastucture.repositories

import com.bookStorage.core.dto.BookModelDto
import com.bookStorage.core.models.BookModel
import com.bookStorage.core.models.CellModel
import com.bookStorage.core.models.GenreModel
import com.bookStorage.core.repositories.IBookRepo
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root

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

            println(cell.id)

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
            session.merge(bookModel)
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

        val cb = session.criteriaBuilder

        val delQuery = cb.createCriteriaDelete(BookModel::class.java)
        val root = delQuery.from(BookModel::class.java)

        delQuery.where(cb.equal(root.get<BookModel>("id"), id))

        var result: Boolean = false

        try {
            transaction.begin()
            session.createMutationQuery(delQuery).executeUpdate()
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

        val session: Session = sessionFactory.openSession()

        val cb: CriteriaBuilder = session.criteriaBuilder

        // просто получение всех книг с ограничением по станице и размером странице
        val getAll = cb.createQuery(BookModel::class.java)
        var root = getAll.from(BookModel::class.java)

        getAll.select(root)

        val query = session.createQuery(getAll)

        // настройка выдаваемых записей по странице
        query.setFirstResult((bookPage-1)*pageSize)
        query.setMaxResults(pageSize)


        val result = query.resultList

        session.close()

        return result
    }

    override fun GetBookById(id: UUID): BookModel {
        val session = sessionFactory.openSession()

        val cb = session.criteriaBuilder
        val criteriaQuery = cb.createQuery(BookModel::class.java)
        val root = criteriaQuery.from(BookModel::class.java)

        criteriaQuery.select(root).where(cb.equal(root.get<BookModel>("id"), id))

        val result = session.createQuery(criteriaQuery).uniqueResult()
        session.close()

        return result
    }

    override fun GetBooksByAuthorSearch(name: String): List<BookModel> {

        val pageSize = 15

        val session: Session = sessionFactory.openSession()

        val criteriaBuilder = session.criteriaBuilder

        val criteriaQuery = criteriaBuilder.createQuery(BookModel::class.java)
        val root = criteriaQuery.from(BookModel::class.java)

        criteriaQuery.select(root)
        criteriaQuery.where(criteriaBuilder.like(root.get<String>("authorName"), name))

        val query = session.createQuery(criteriaQuery)

        query.setFirstResult(0)
        query.setMaxResults(pageSize)

        val result = query.resultList

        session.close()

        return result
    }

    override fun GetBooksByAuthorByPage(name: String, page: Int, pageSize: Int): List<BookModel> {
        val session: Session = sessionFactory.openSession()

        val criteriaBuilder = session.criteriaBuilder

        val criteriaQuery = criteriaBuilder.createQuery(BookModel::class.java)
        val root = criteriaQuery.from(BookModel::class.java)

        criteriaQuery.select(root)

        val query = session.createQuery(criteriaQuery)

        query.setFirstResult((page-1)*pageSize)
        query.setMaxResults(pageSize)

        val result = query.resultList

        session.close()

        return result
    }

    override fun GetBooksByGenre(genre: GenreModel, pageSize: Int, page: Int): List<BookModel> {
        val session: Session = sessionFactory.openSession()

        val criteriaBuilder = session.criteriaBuilder

        val criteriaQuery = criteriaBuilder.createQuery(BookModel::class.java)
        val root = criteriaQuery.from(BookModel::class.java)

        criteriaQuery.select(root)

        val query = session.createQuery(criteriaQuery)

        query.setFirstResult((page-1)*pageSize)
        query.setMaxResults(pageSize)

        val result = query.resultList

        session.close()

        return result
    }

    override fun GetBookByName(name: String, pageSize: Int, page: Int): List<BookModel> {
        val session: Session = sessionFactory.openSession()

        val criteriaBuilder = session.criteriaBuilder

        val criteriaQuery = criteriaBuilder.createQuery(BookModel::class.java)
        val root = criteriaQuery.from(BookModel::class.java)

        criteriaQuery.select(root)

        val query = session.createQuery(criteriaQuery)

        query.setFirstResult((page-1)*pageSize)
        query.setMaxResults(pageSize)

        val result = query.resultList

        session.close()

        return result
    }
}