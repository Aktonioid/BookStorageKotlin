package com.bookStorage.infrastucture.repositories

import com.bookStorage.core.models.GenreModel
import com.bookStorage.core.repositories.IGenreRepo
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class GenreRepo(var sessionFactory: SessionFactory): IGenreRepo{

    override fun CreateGenre(genre: GenreModel): Boolean {
        val session: Session = sessionFactory.openSession()
        var transaction = session.transaction

        try {
            transaction.begin()
            session.persist(genre)
            session.flush()
            transaction.commit()
        }
        catch(e: HibernateException){
            transaction.rollback()
            e.printStackTrace()
            return false
        }
        catch (e: Exception){
            e.printStackTrace()
            return false
        }
        session.close()

        return true
    }

    override fun GetGenreById(id: UUID): GenreModel {

        val session: Session = sessionFactory.openSession()

        val queryString: String = "from GenreModel where id=:id"

        var query = session.createQuery(queryString, GenreModel::class.java)

        query.setParameter("id", id)

        val genre: GenreModel = query.singleResult

        session.close()

        return genre
    }

    override fun GetAllGenres(): List<GenreModel> {

        val session: Session = sessionFactory.openSession()

        val queryString: String = "from GenreModel"

        val result = session.createQuery(queryString, GenreModel::class.java).resultList

        session.close()

        return result
    }

    override fun DeleteGenresById(id: UUID): Boolean {

        var result: Boolean = false

        val session: Session = sessionFactory.openSession()
        val transaction: Transaction = session.transaction

        val stringQuery: String = "delete GenreModel g where g.id=:id"

        val query = session.createMutationQuery(stringQuery)

        query.setParameter("id", id)

        try {
            transaction.begin()
            query.executeUpdate()
            session.flush()
            transaction.commit()
            result = true
        }
        catch(e: HibernateException){
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

    override fun UpdateGenre(genre: GenreModel): Boolean {
        var result: Boolean = false

        val session: Session = sessionFactory.openSession()
        val transaction: Transaction = session.transaction

        val hqlString: String = "update GenreModel g set g.name=:name where g.id=:id "

        val updateQuery = session.createMutationQuery(hqlString)
        updateQuery.setParameter("name", genre.name)
        updateQuery.setParameter("id", genre.id)

        try {
            transaction.begin()
            updateQuery.executeUpdate()
            session.flush()
            transaction.commit()
            result = true
        }
        catch (e: HibernateException){
            transaction.rollback()
            e.printStackTrace()
        }
        catch(e: Exception){
            transaction.rollback()
            e.printStackTrace()
        }
        finally {
            session.close()
        }

        return result
    }


}