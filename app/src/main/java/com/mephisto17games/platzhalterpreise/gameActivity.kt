package com.mephisto17games.platzhalterpreise

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.roundToInt


class gameActivity : AppCompatActivity() {

    // set max rounds and Start
    private var currentRound: Int = 0

    // show toast with delay 3 seconds
    fun toastDelay() {
        val handler = Handler()
        //handler.postDelayed({ showWinnerToast() }, 1000)
    }

    // generate Toast
    fun showWinnerToast(winner: String, points: Int){
        //Toasttext
        Toast.makeText(this, "$winner wins $points points!", Toast.LENGTH_SHORT).show()
    }

    fun showDrawToast(){
        //Toasttext
        Toast.makeText(this, "Draw! Nobody wins any points!", Toast.LENGTH_SHORT).show()
    }
    fun getCPUguess(price: Float, deviation: Int):Float{
        CPUguess = price * Random.nextInt((100-deviation),(100+deviation)) /100
        Log.d("wildcardwillies",CPUguess.toString())
        return CPUguess
        //player2Price.text = df.format(CPUguess).toString()

    }

    var CPUguess = .0f
    var playerGuess = .0f
    val df = DecimalFormat("0.00")
    //df.roundingMode = RoundingMode.CEILING



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)

        var alreadGuessed=false
        var player1ScoreValue: Int = 0
        var player2ScoreValue: Int = 0
        var player1PriceValue: Float = 0F
        var player2PriceValue: Float = 0F

        // Zugriff auf itemImage
        val itemImageDisplay = findViewById<ImageView>(R.id.itemImage)
        val itemNameDisplay = findViewById<TextView>(R.id.itemName)
        val itemPriceDisplay = findViewById<TextView>(R.id.itemPrice)

        val player1Name = findViewById<TextView>(R.id.p1Name)
        val bundle = intent.extras

            val rounds2Play = bundle.getInt("rounds")

            val name = bundle.getString("player1Name")
        player1Name.text = name.toString()

        val player2Name = findViewById<TextView>(R.id.p2Name)
            val name2 = bundle.getString("player2Name")
        player2Name.text = name2.toString()

        // Schwierigkeitsgrad -> Abweichung von 100% der Schätzung vom CPU
        val difficulty = bundle.getInt("difficulty")
        var deviation: Int = 40
        when(difficulty)
        {
            0 -> deviation = 40
            1 -> deviation = 20
            2 -> deviation = 10
        }

        var player1Score = findViewById<TextView>(R.id.p1Score)
        player1Score.text = player1ScoreValue.toString()
        var player2Score = findViewById<TextView>(R.id.p2Score)
        player2Score.text = player2ScoreValue.toString()

        var player1Price = findViewById<TextView>(R.id.p1Price)
        player1Price.text = getString(R.string.emptyPrice)

        var player2Price = findViewById<TextView>(R.id.p2Price)
        player2Price.text = getString(R.string.emptyPrice)

        val inputPrice = findViewById<EditText>(R.id.inputPrice)

        // Array Bilder
        val itemImageArray = intArrayOf(
            R.drawable.a0001,
            R.drawable.a0002,
            R.drawable.a0003,
            R.drawable.a0004,
            R.drawable.a0005,
            R.drawable.a0006,
            R.drawable.a0007,
            R.drawable.a0008,
            R.drawable.a0009,
            R.drawable.a0010,
            R.drawable.a0011,
            R.drawable.a0012,
            R.drawable.a0013,
            R.drawable.a0014,
            R.drawable.a0015,
            R.drawable.a0016,
            R.drawable.a0017,
            R.drawable.a0018,
            R.drawable.a0019,
            R.drawable.a0020,
            R.drawable.a0021,
            R.drawable.a0022,
            R.drawable.a0023,
            R.drawable.a0024,
            R.drawable.a0025,
            R.drawable.a0026,
            R.drawable.a0027,
            R.drawable.a0028,
            R.drawable.a0029,
            R.drawable.a0030,
            R.drawable.a0031,
            R.drawable.a0032,
            R.drawable.a0033,
            R.drawable.a0034,
            R.drawable.a0035,
            R.drawable.a0036,
            R.drawable.a0037,
            R.drawable.a0038,
            R.drawable.a0039,
            R.drawable.a0040,
            R.drawable.a0041,
            R.drawable.a0042,
            R.drawable.a0043,
            R.drawable.a0044,
            R.drawable.a0045,
            R.drawable.a0046,
            R.drawable.a0047,
            R.drawable.a0048,
            R.drawable.a0049,
            R.drawable.a0050
            /*R.drawable.a0051,
            R.drawable.a0052,
            R.drawable.a0053,
            R.drawable.a0054,
            R.drawable.a0055,
            R.drawable.a0056,
            R.drawable.a0057,
            R.drawable.a0058,
            R.drawable.a0059,
            R.drawable.a0060,
            R.drawable.a0061,
            R.drawable.a0062,
            R.drawable.a0063,
            R.drawable.a0064,
            R.drawable.a0065,
            R.drawable.a0066,
            R.drawable.a0067,
            R.drawable.a0068,
            R.drawable.a0069,
            R.drawable.a0070,
            R.drawable.a0071,
            R.drawable.a0072,
            R.drawable.a0073,
            R.drawable.a0074,
            R.drawable.a0075,
            R.drawable.a0076,
            R.drawable.a0077,
            R.drawable.a0078,
            R.drawable.a0079,
            R.drawable.a0080,
            R.drawable.a0081,
            R.drawable.a0082,
            R.drawable.a0083,
            R.drawable.a0084,
            R.drawable.a0085,
            R.drawable.a0086,
            R.drawable.a0087,
            R.drawable.a0088,
            R.drawable.a0089,
            R.drawable.a0090,
            R.drawable.a0091,
            R.drawable.a0092,
            R.drawable.a0093,
            R.drawable.a0094,
            R.drawable.a0095,
            R.drawable.a0096,
            R.drawable.a0097,
            R.drawable.a0098,
            R.drawable.a0099,
            R.drawable.a0100*/)

        // Array Produktnamen
        val itemNameArray = arrayOf(
            "Hipster Camera",
            "Sparkling Metallic Device To Play",
            "One Glass Of Milk",
            "Appetizer Basil Bites",
            "Corn On The Co**",
            "Ice Cream Cone",
            "Drone",
            "Fancy Socks",
            "XL Sneakers",
            "Android Buddy",
            "Cup Of Coffee",
            "Espresso",
            "Tasty Waffles",
            "Macarons",
            "Chocolate Chip Cookie",
            "Car",
            "Snowboard",
            "Uncomfortable Designer Chair",
            "Couch",
            "Overpriced Fruit Laptop",
            "Grumpy Teddy \"Etienne\"",
            "Hipster Accident For Hobbyists",
            "Business Shirt",
            "Party Pineapple",
            "Flashy Sunglasses",
            "Beach Head",
            "Super Real Amethyst Ring",
            "Diamond Necklace",
            "Beauty Pie",
            "Slice Of Pizza",
            "Donut",
            "Sushi Box",
            "Blow Dryer",
            "Red Lipstick \"Freudenspender\"",
            "Humming Top",
            "Rubber Duckling",
            "Fidget Spinner",
            "PlayStation 4",
            "Crayons",
            "Burger",
            "Cucumber \"Dicky\"",
            "Aubergine",
            "Smooth and Spiky \"Banana\"",
            "RBTV Supporters Club (Basic)",
            "Budi Head",
            "Bow \"Geronimo\" and Arrow (mythical)",
            "Bicycle",
            "Wiener",
            "Vacuum",
            "Water - Without Taste, Color, Calories, Fat, and Genes")

        // Array Preise
        val itemPriceArray = arrayOf(
            379.90,
            62.50,
            0.21,
            8.50,
            0.49,
            1.50,
            1249.00,
            18.25,
            89.59,
            5.80,
            2.50,
            1.98,
            6.30,
            8.20,
            0.75,
            55285.00,
            439.89,
            208.00,
            762.69,
            1326.99,
            16.39,
            155.00,
            51.19,
            2.00,
            24.49,
            14.02,
            37.89,
            1034.00,
            24.00,
            2.38,
            2.39,
            15.45,
            19.56,
            9.44,
            14.92,
            8.12,
            9.94,
            396.00,
            11.31,
            8.45,
            1.20,
            1.05,
            0.38,
            5.00,
            119.90,
            67.56,
            172.95,
            0.36,
            259.00,
            4.50)

        // unversalButton Text -> Guess
        val submitGuessButton = findViewById<Button>(R.id.universalButton)
        submitGuessButton.text = getString(R.string.guessText)

        // Random Start
        var whoFirst = Random.nextInt(1,3)

        // Musik fortsetzen
        Music.play(this, R.raw.bensound_elevator)


        // Array mit zufällig gewählten Artikeln aus Liste, ohne Zurücklegen
        var selectedArticles = (0..itemImageArray.size-1).toList()
        Log.d("wildcardwillies",selectedArticles.toString())
        selectedArticles=selectedArticles.shuffled().take(rounds2Play)
        Log.d("wildcardwillies",selectedArticles.toString())

        // durchgehen des Array
        var number = selectedArticles[currentRound]
        itemImageDisplay.setImageResource(itemImageArray[number])
        itemNameDisplay.setText(itemNameArray[number])
        //itemPriceDisplay.setText(itemPriceArray[number].toString())

        Log.d("wildcardwillies",whoFirst.toString())

        // Start CPU immer als erstes
        //whoFirst = 2

        // CPU fängt an
        if(whoFirst == 2)
        {
            CPUguess= getCPUguess(itemPriceArray[number].toFloat(),deviation)
            //var CPUguess = itemPriceArray[number] * Random.nextInt(60,140) /100
            Log.d("wildcardwillies",CPUguess.toString())
            player2Price.text = df.format(CPUguess).toString()
            player2PriceValue=CPUguess
        }

        // OnClickListener:
        submitGuessButton.setOnClickListener(View.OnClickListener {

            if(alreadGuessed==false)
            {
                playerGuess = inputPrice.text.toString().toFloat()
                Log.d("wildcardwillies", playerGuess.toString())
                player1Price.text = df.format(playerGuess).toString()
                player1PriceValue=playerGuess
                if (whoFirst == 1) {
                    CPUguess= getCPUguess(itemPriceArray[number].toFloat(),deviation)
                    Log.d("wildcardwillies", CPUguess.toString())
                    player2Price.text = df.format(CPUguess).toString()
                    player2PriceValue=CPUguess
                }

                // sleep 1 second
                //TimeUnit.SECONDS.sleep(1L)
                TimeUnit.MILLISECONDS.sleep(400L)

                alreadGuessed = true
                itemPriceDisplay.setText(df.format(itemPriceArray[number]).toString())

                var p1Diff = abs(itemPriceArray[number]-player1PriceValue)
                var p2Diff = abs(itemPriceArray[number]-player2PriceValue)

                Log.d("wildcardwillies", p1Diff.toString())
                Log.d("wildcardwillies", p2Diff.toString())

                // Unterschied zwischen den Schätzungen
                if(abs(p1Diff-p2Diff) < 0.005) // draw
                {
                    showDrawToast()
                }else if (p1Diff < p2Diff) // P1 wins
                {
                    var points = 1
                    // genauer Treffer des Preises
                    if (p1Diff < 0.005)
                    {
                        // Deckelung bei 1000 Punkte, Schutz vor Integer-Überlauf
                        points = 1000
                        if (itemPriceArray[number].roundToInt() < 1000)
                        {
                            points = itemPriceArray[number].roundToInt()
                        }
                        // falls Preis richtig geraten ist, aber unter 0.50 EUR: points -> 1
                        if (points == 0)
                        {
                            points = 1
                        }
                    }


                    showWinnerToast(player1Name.text.toString(),points)
                    player1ScoreValue += points
                    player1Score.text=player1ScoreValue.toString()

                    val mp = MediaPlayer.create(this, R.raw.coin01)
                    mp.start()

                }else
                //(p1Diff > p2Diff) P2 wins
                {
                    var points=1
                    if (p2Diff<0.01)
                    {
                        points = 1000
                        if (itemPriceArray[number].roundToInt() < 1000)
                        {
                            points = itemPriceArray[number].roundToInt()
                        }
                    }
                    showWinnerToast(player2Name.text.toString(),points)
                    player2ScoreValue += points
                    player2Score.text=player2ScoreValue.toString()

                    val mp = MediaPlayer.create(this, R.raw.loser01)
                    mp.start()
                }

                // unversalButton Text -> Next Round
                submitGuessButton.text = getString(R.string.nextRoundText)
                // unversalButton Text -> Last Round
                if (currentRound == rounds2Play-2) {submitGuessButton.text = getString(R.string.lastRoundText)}
                // unversalButton Text -> Results
                if (currentRound >= rounds2Play-1) {submitGuessButton.text = getString(R.string.resultsText)}

            } else
            {
                //
                // Winner and Loser + Points
                //

                alreadGuessed=false

                val winnerName :String
                val winnerScore :String
                val loserName :String
                val loserScore :String

                if (currentRound >= rounds2Play-1) {
                    val myIntent = Intent(this@gameActivity, endActivity::class.java)
                    if(player1ScoreValue>=player2ScoreValue)
                    {
                         winnerName = player1Name.text.toString()
                         winnerScore = player1Score.text.toString()
                         loserName = player2Name.text.toString()
                         loserScore = player2Score.text.toString()
                    }else
                    {
                         winnerName = player2Name.text.toString()
                         winnerScore = player2Score.text.toString()
                         loserName = player1Name.text.toString()
                         loserScore = player1Score.text.toString()
                    }

                    // Musik pausieren/stoppen
                    Music.stop(this)

                    myIntent.putExtra("winnerName",winnerName)
                    myIntent.putExtra("winnerScore",winnerScore)
                    myIntent.putExtra("loserName",loserName)
                    myIntent.putExtra("loserScore",loserScore)
                    startActivity(myIntent)
                }

                //
                // Init neue Runde
                //

                // unversalButton Text -> Guess
                if (currentRound < rounds2Play-1) {
                    submitGuessButton.text = getString(R.string.guessText)

                    currentRound++

                // zurücksetzen der Eingaben und verdecken des Preises
                    number = selectedArticles[currentRound]
                    itemImageDisplay.setImageResource(itemImageArray[number])
                    itemNameDisplay.setText(itemNameArray[number])
                    itemPriceDisplay.text = getString(R.string.itemprice)
                    player1Price.text = getString(R.string.emptyPrice)
                    player2Price.text = getString(R.string.emptyPrice)

                // ändern des Spielers, der zu erst seinen Preis eingibt
                    when(whoFirst)
                    {
                        2 -> whoFirst=1
                        1 -> {
                                whoFirst = 2
                                CPUguess = getCPUguess(itemPriceArray[number].toFloat(),deviation)
                                player2Price.text = df.format(CPUguess).toString()
                                player2PriceValue=CPUguess
                            }
                    }

                    toastDelay()
                }
            }
        })
    }

}