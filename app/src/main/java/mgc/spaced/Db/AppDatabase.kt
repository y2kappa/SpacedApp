package mgc.spaced.Db

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mgc.spaced.Db.QuoteDb

@Database(entities = arrayOf(QuoteDb::class), version = 1)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quotesDao(): QuotesDao
//    abstract fun noteDAO(): NoteDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room
                .databaseBuilder(context, AppDatabase::class.java, "spaced.db")
                .build()

        fun destroyDataBase() {
            instance = null
        }

        public suspend fun getQueries(db : AppDatabase) : ArrayList<QuoteDb> {
            return ArrayList<QuoteDb>(db.quotesDao().getAll())
        }
    }

}