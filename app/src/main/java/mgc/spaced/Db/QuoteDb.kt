package mgc.spaced.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "quotes")
data class QuoteDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "body") var body: String,
    @ColumnInfo(name = "date") var content: Date
)