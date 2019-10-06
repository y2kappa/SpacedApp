package mgc.spaced.Db

import androidx.room.*
import java.util.*

@Dao
interface QuotesDao {
    @Query("SELECT * FROM quotes")
    fun getAll(): List<QuoteDb>

//    @Query("SELECT * FROM todoentity WHERE title LIKE :title")
//    fun findByTitle(title: String): TodoEntity

    @Insert
    fun insertAll(vararg quotes: QuoteDb)

    @Delete
    fun delete(todo: QuoteDb)

    @Update
    fun updateTodo(vararg todos: QuoteDb)
}
