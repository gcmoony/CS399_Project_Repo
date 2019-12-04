package TeamGridLock.GridLockPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define Buttons
        val top_left = findViewById<Button>(R.id.top_left)
        val top_center = findViewById<Button>(R.id.top_center)
        val top_right = findViewById<Button>(R.id.top_right)
        val left = findViewById<Button>(R.id.left)
        val center = findViewById<Button>(R.id.center)
        val right = findViewById<Button>(R.id.right)
        val bottom_left = findViewById<Button>(R.id.bottom_left)
        val bottom_center = findViewById<Button>(R.id.bottom_center)
        val bottom_right = findViewById<Button>(R.id.bottom_right)
        var p1_win = 0
        var p2_win = 0

        fun updateScore(winning_player : Int){
            when(winning_player){
                0 -> p1_win++
                1 -> p2_win++
            }
            player1_count.text = p1_win.toString()
            player2_count.text = p2_win.toString()
        }

        fun allowClicks(flag: Boolean){
            top_left.isClickable = flag
            top_center.isClickable = flag
            top_right.isClickable = flag
            left.isClickable = flag
            center.isClickable = flag
            right.isClickable = flag
            bottom_left.isClickable = flag
            bottom_center.isClickable = flag
            bottom_right.isClickable = flag
        }

        fun resetGame(){
            // Reset spots
            top_left.text = ""
            top_center.text = ""
            top_right.text = ""
            left.text = ""
            center.text = ""
            right.text = ""
            bottom_left.text = ""
            bottom_center.text = ""
            bottom_right.text = ""

            // Remove views
            win_view.visibility = View.INVISIBLE
            win_view2.visibility = View.INVISIBLE
            reset_button.visibility = View.INVISIBLE
            reset_button.isClickable = false

            // Enable clicks
            allowClicks(true)
        }

        fun displayEndMessage(win_type: Int){
            when(win_type){
                0 -> win_view.text = getString(R.string.x_win)
                1 -> win_view.text = getString(R.string.o_win)
                2 -> win_view.text = getString(R.string.no_win)
            }
            if(win_type != 2)
                updateScore(win_type)
            win_view.visibility = View.VISIBLE
            win_view2.visibility = View.VISIBLE
            reset_button.visibility = View.VISIBLE
            reset_button.isClickable = true
            allowClicks(false)
        }

        fun checkRows(){
            if(top_left.text == top_center.text && top_center.text == top_right.text
                && top_right.text != "") {
                if (top_center.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
            else if(left.text == center.text && center.text == right.text
                && right.text != "") {
                if (center.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
            else if(bottom_left.text == bottom_center.text && bottom_center.text == bottom_right.text
                && bottom_right.text != "") {
                if (bottom_center.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
        }

        fun checkCols(){
            // Left Check
            if(top_left.text == left.text && bottom_left.text == left.text
                && left.text != "") {
                if (left.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
            // Center Check
            else if(top_center.text == center.text && center.text == bottom_center.text
                && center.text != "") {
                if (center.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
            // Right Check
            else if(bottom_right.text == right.text && bottom_right.text == top_right.text
                && bottom_right.text != "") {
                if (bottom_right.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
        }

        fun checkDiag(){
            if(top_left.text == center.text && center.text == bottom_right.text
                && center.text != "") {
                if(center.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
            else if(top_right.text == center.text && center.text == bottom_left.text
                && center.text != ""){
                if(center.text == "X")
                    displayEndMessage(0)
                else
                    displayEndMessage(1)
            }
        }

        fun checkBoard(){
            if( top_left.text != "" &&
                top_center.text != "" &&
                top_right.text != "" &&
                left.text != "" &&
                center.text != "" &&
                right.text != "" &&
                bottom_left.text != "" &&
                bottom_center.text != "" &&
                bottom_right.text != "")
                displayEndMessage(2)
        }

        fun switchPlayer(){
            if(player_turn_str.text == getString(R.string.player_turn_x_str))
                player_turn_str.text = getString(R.string.player_turn_o_str)
            else
                player_turn_str.text = getString(R.string.player_turn_x_str)
        }

        fun clickListener(view: View) {
            val aButton = findViewById<Button>(view.id)
            if(aButton.text == ""){
                if(player_turn_str.text == getString(R.string.player_turn_x_str))
                    aButton.text = "X"
                else
                    aButton.text = "O"
                switchPlayer()
            }
            checkRows()
            checkCols()
            checkDiag()
            checkBoard()
        }


        top_left.setOnClickListener {clickListener((it))}
        top_center.setOnClickListener {clickListener((it))}
        top_right.setOnClickListener {clickListener((it))}
        left.setOnClickListener {clickListener((it))}
        center.setOnClickListener {clickListener((it))}
        right.setOnClickListener {clickListener((it))}
        bottom_left.setOnClickListener {clickListener((it))}
        bottom_center.setOnClickListener {clickListener((it))}
        bottom_right.setOnClickListener {clickListener((it))}
        reset_button.setOnClickListener{resetGame()}

        }

    }


