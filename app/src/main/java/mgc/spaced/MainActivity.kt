package mgc.spaced

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import mgc.spaced.Db.AppDatabase
import kotlinx.coroutines.launch
import mgc.spaced.Db.QuoteDb
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG = "Spaced-Main"

    private var job: Job = Job()

    private var mQuotes: ArrayList<QuoteDb> = ArrayList()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val quotes = ArrayList<String>()

        quotes.add("Whether a challenge will be met depends on the presence or absence of creative individuals with with clarity of mind and energy of will (almost a definition of genius) capable of effective responses to new situations (almost a definition of intelligence).")

        quotes.add("If a man does not keep pace with his companions, perhaps it is because he hears a different drummer. Let him step to the music which he hears, however measured or far away.")

        quotes.add("To learn is to broaden, to experience more, to snatch new aspects of life for yourself. To refuse to learn or to be relieved at not having to learn is to commit a form of suicide; in the long run, a more meaningful type of suicide than the mere ending of physical life. \n" + "\n" + "Knowledge is not only power; it is happiness, and being taught is the intellectual analog of being loved.")

        val typefaceMontBold = Typeface.createFromAsset(assets, "Mont-Bold.ttf")
        val typefaceMontBook = Typeface.createFromAsset(assets, "Mont-Book.ttf")
        val quoteTextView: TextView = findViewById(R.id.main_quote)

        var currentQuote = 0

        quoteTextView.text = quotes[currentQuote]
        quoteTextView.typeface = typefaceMontBook

        quoteTextView.setOnClickListener {
            currentQuote = (currentQuote + 1) % quotes.size
            quoteTextView.text = quotes[currentQuote]
            Log.i(TAG, "Current Quote Index is: $currentQuote")
        }

        val plusTextView: TextView = findViewById(R.id.main_bar_plus)
        plusTextView.typeface = typefaceMontBold

        val db = AppDatabase(this)
        launch(Dispatchers.Default) {
            val queries = AppDatabase.getQueries(db)
            Log.i(TAG, "Received quesies ${queries.size}")
            mQuotes = queries
//            onResult(queries)
        }

    }

//    fun onResult(result: ArrayList<QuoteDb>) {
//        Log.i(TAG, "Received quesies ${result.size}")
//        mQuotes = result
//    }

}
