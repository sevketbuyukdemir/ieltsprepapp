package com.sevketbuyukdemir.ieltsprepapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sevketbuyukdemir.ieltsprepapp.Fragments.ListeningFragment
import com.sevketbuyukdemir.ieltsprepapp.utils.Question
import com.sevketbuyukdemir.ieltsprepapp.view_adapters.ListeningActivityViewPagerAdapter


class ListeningActivity : AppCompatActivity() {
    val context : Context = this

    /**
     * We can still use [@JvmStatic] for 'var' variables, but it generates getter/setters as
     * functions of KotlinClass. If we use 'val' instead, it only generates a getter function
     */
    companion object {
        private var instance: ListeningActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        @JvmStatic
        lateinit var firstSectionAnswers : Array<String>
        @JvmStatic
        lateinit var secondSectionAnswers : Array<String>
        @JvmStatic
        lateinit var thirdSectionAnswers : Array<String>
        @JvmStatic
        lateinit var forthSectionAnswers : Array<String>

        // Answers arraylists this arraylist include right answers
        private val answersFirstSection = ArrayList<String>()
        private val answersSecondSection = ArrayList<String>()
        private val answersThirdSection = ArrayList<String>()
        private val answersForthSection = ArrayList<String>()

        @JvmStatic
        var submittedSectionCount : Int = 0

        @JvmStatic
        fun submitAllListeningSections(){
            var gradeListening = 0
            if(submittedSectionCount == 4) {
                var counter : Int = 0
                while (counter < 10){
                    if(firstSectionAnswers[counter] == answersFirstSection[counter]){
                        gradeListening++
                    }
                    counter++
                }
                counter = 0
                while (counter < 10){
                    if(secondSectionAnswers[counter] == answersSecondSection[counter]){
                        gradeListening++
                    }
                    counter++
                }
                counter = 0
                while (counter < 10){
                    if(thirdSectionAnswers[counter] == answersThirdSection[counter]){
                        gradeListening++
                    }
                    counter++
                }
                counter = 0
                while (counter < 10){
                    if(forthSectionAnswers[counter] == answersForthSection[counter]){
                        gradeListening++
                    }
                    counter++
                }
                println("Your grade : $gradeListening")
                Toast.makeText(ListeningActivity.applicationContext(), "Your grade : $gradeListening", Toast.LENGTH_LONG).show()
                val intent = Intent(ListeningActivity.applicationContext(), MainActivity::class.java)
                ListeningActivity.applicationContext().startActivity(intent)
            }
        }

        private fun fillAnswersArrayList(){
            answersFirstSection.add("Marshall")
            answersFirstSection.add("180 days")
            answersFirstSection.add("3.85 (%)")
            answersFirstSection.add("Monthly Interest and monthly interest")
            answersFirstSection.add("maximum")
            answersFirstSection.add("income bracket")
            answersFirstSection.add("120,000 / 120000")
            answersFirstSection.add("no fees")
            answersFirstSection.add("quarterly")
            answersFirstSection.add("(by) phone")

            answersSecondSection.add("to remember the history of the park")
            answersSecondSection.add("1979")
            answersSecondSection.add("a petting zoo")
            answersSecondSection.add("tall and made of wood")
            answersSecondSection.add("black")
            answersSecondSection.add("food stands")
            answersSecondSection.add("Main Street / St. / main street / st.")
            answersSecondSection.add("Hollywood")
            answersSecondSection.add("first aid")
            answersSecondSection.add("guard stations")

            answersThirdSection.add("task instructions")
            answersThirdSection.add("strategies")
            answersThirdSection.add("case studies")
            answersThirdSection.add("action plan")
            answersThirdSection.add("supply")
            answersThirdSection.add("drains")
            answersThirdSection.add("laundry")
            answersThirdSection.add("manufacturers")
            answersThirdSection.add("residential")
            answersThirdSection.add("competition")

            answersForthSection.add("plan")
            answersForthSection.add("agriculture")
            answersForthSection.add("arrows")
            answersForthSection.add("third")
            answersForthSection.add("we can adapt to a range of diets.")
            answersForthSection.add("in the past they didn’t farm cows")
            answersForthSection.add("high fat")
            answersForthSection.add("practices")
            answersForthSection.add("nut oils")
            answersForthSection.add("environment")
        }
    }

    // Question arraylists this arraylist include Question objects and necessary data
    private val questionFirstSection = ArrayList<Question>()
    private val questionSecondSection = ArrayList<Question>()
    private val questionThirdSection = ArrayList<Question>()
    private val questionForthSection = ArrayList<Question>()

    // View pager and Fragments
    private lateinit var listeningActivityViewPagerAdapter: ListeningActivityViewPagerAdapter
    private lateinit var listeningFragmentSectionFirst : ListeningFragment
    private lateinit var listeningFragmentSectionSecond : ListeningFragment
    private lateinit var listeningFragmentSectionThird : ListeningFragment
    private lateinit var listeningFragmentSectionForth : ListeningFragment

    // Global objects for UI elements
    private lateinit var listeningTabLayout : TabLayout
    private lateinit var listeningViewpager : ViewPager

    /**
     * First triggered function of ListeningActivity.
     * This function call init() function
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)
        init()
        ListeningActivity.instance = this
    }

    /**
     * Function for initialize UI elements and necessary adapters and data type
     */
    private fun init(){
        // Bind views with Ids
        listeningTabLayout = findViewById(R.id.listening_tab_layout)
        listeningViewpager = findViewById(R.id.listening_viewpager)
        fillQuestionArrayLists()
        fillAnswersArrayList()
        initiateAnswerArrays()
        initiateFragments()
        setAndInitiateViewPagerAdapter()
    }

    /**
     * '^' character is special character for put blank to fill in the blank question
     */
    private fun fillQuestionArrayLists(){
        // Fill first section questions array to arraylist
        questionFirstSection.add(Question(false, "Customer name: David 1 ^"))
        questionFirstSection.add(Question(false, "Phone: 023 - 561- 055\n" +
                "D.O.B.: 18 / 02 / 1968\n"+
                "Customer’s Term Deposit details:\n" +
                "Amount: \$18,000 \n Term: 2 ^"))
        questionFirstSection.add(Question(false, "Interest rate: 3.45% per annum \n" +
                "Current Term Deposit interest rates:\n" +
                "1 year 3.65% per annum\n2 years 3 ^ % per annum"))
        questionFirstSection.add(Question(false, "4 ^ Term Deposits"))
        questionFirstSection.add(Question(false, "Minimum deposit: \$20,000\n" +
                "5 ^ tax rate: 28%"))
        questionFirstSection.add(Question(false, "Investment returns Depend on 6 ^"))
        questionFirstSection.add(Question(false, "Salary \$70,001 – 7 \$ ^ 3.92. % per annum"))
        questionFirstSection.add(Question(false, "Hidden charges/fees: 8 ^"))
        questionFirstSection.add(Question(false, "Interest payment options:\n" +
                "monthly, 9 ^ , 6-monthly, annually"))
        questionFirstSection.add(Question(false, "Application options:\n" +
                "online 10 ^ in person"))
        // Fill second section questions array to arraylist
        questionSecondSection.add(Question(true,
            "11 - The September Celebration day is held ...",
            arrayOf("five times a year to honour the city",
                "on the park’s important birthday",
                "to remember the history of the park")))
        questionSecondSection.add(Question(true,
            "12 - The park was first built in ...",
            arrayOf("1955",
                "1979",
                "the 1990s")))
        questionSecondSection.add(Question(true,
            "13 - The park still uses ...",
            arrayOf("a children’s play area",
                "a petting zoo",
                "two of the early rides")))
        questionSecondSection.add(Question(true,
            "14 - The Hurricane roller-coaster is ...",
            arrayOf("tall and made of wood",
                "designed for smaller children",
                "very fast and exciting")))
        questionSecondSection.add(Question(true,
            "15 - The rides with a height limit are coded ...",
            arrayOf("yellow",
                "blue",
                "black")))
        questionSecondSection.add(Question(false, "Food options:\n" +
                "• Italian, Chinese, etc. at the Food Court\n" +
                "• hamburgers, sandwiches, etc. at 16 ^"))
        questionSecondSection.add(Question(false, "Special Events:\n" +
                "Parade\n" +
                "• Starts at noon\n" +
                "• On the 17 ^ \n" +
                "• Run by final year high school students"))
        questionSecondSection.add(Question(false, "Concert\n" +
                "• At the amphitheatre\n" +
                "• Theme: 18 ^\n" +
                "• Starts at 7:00"))
        questionSecondSection.add(Question(false, "Safety and Security:\n" +
                "• Ten 19 ^ centres in the park\n" +
                "• Children ask any staff member for help"))
        questionSecondSection.add(Question(false, "• Ask security team at the 20 ^"))
        // Fill third section questions array to arraylist
        questionThirdSection.add(Question(false, "Project topic: design a water treatment system\n" +
                "Tutorial structure:\n" +
                "Step 1: go over 21 ^"))
        questionThirdSection.add(Question(false, "Step 2: think about research 22 ^ \n" +
                "• search online databases using good search terns"))
        questionThirdSection.add(Question(false, "• consider the kind of research, e.g. 23 ^ from\n" +
                "other projects"))
        questionThirdSection.add(Question(false, "Step 3: develop an 24 ^."))
        questionThirdSection.add(Question(false, "Project description:\n" +
                "You need to design a grey-water \n" +
                "treatment system to reduce the \n" +
                "pressure on the water 25 ^ in a Cameroon village. \n" +
                "Grey-water is wastewater from household"))
        questionThirdSection.add(Question(false, "26 ^ . The system needs to treat this water \n to remove bacteria, and recycle it to"))
        questionThirdSection.add(Question(false, "use for purposes such as watering plants, flushing toilets and doing 27 ^."))
        questionThirdSection.add(Question(false, "Research tips\n" +
                "General internet searches:\n" +
                "Avoid websites where 28 ^ try to sell their products."))
        questionThirdSection.add(Question(false, "Engineering library:\n" +
                "Use key words when searching the catalogue\n" +
                "e.g. grey-water treatment systems / 29 ^ use"))
        questionThirdSection.add(Question(false, "EWB website:\n" +
                "Check examples from the 30 ^ last year."))
        // Fill forth section questions array to arraylist
        questionForthSection.add(Question(false, "Origins of the Caveman Diet\n" +
                "There are many popular fad diets nowadays. \nThey all promise good health \nif you stick to the \n" +
                "31 ^."))
        questionForthSection.add(Question(false, "The Caveman diet is a popular example. \nThis diet includes foods such as lean\n" +
                "meat and fish that our forebears\n ate before we developed 32 ^."))
        questionForthSection.add(Question(false, "We need to find out what our ancestors did eat, so " +
                "researchers are studying some existing hunter-gatherer tribes." +
                "These tribes typically like to eat meat but they can’t always get it, even though they are skilled with" +
                "their weapons, e.g. 33 ^."))
        questionForthSection.add(Question(false, "So, instead, they eat foods that their\n" +
                "wives gather. They get only \nabout a 34 ^ of their energy from meat."))
        questionForthSection.add(Question(true,
            "35 - Research evidence suggests that ...",
            arrayOf("the tribesmen’s traditional diet is unhealthy",
                "our bodies can digest only certain foods",
                "we can adapt to a range of diets.")))
        questionForthSection.add(Question(true,
            "36 - Thai people have difficulty digesting milk because ...",
            arrayOf("they have too much lactase in their bodies",
                "in the past they didn’t farm cows",
                "their saliva lacks certain enzymes.")))
        questionForthSection.add(Question(false, "Variation in global diets:\n" +
                "• Inuit – most calories from\n 37 ^ foods, e.g. seal meat"))
        questionForthSection.add(Question(false, "• Jains – vegetarian, but milk is permitted\n" +
                "• Others – fish, insects\n" +
                "Implications for the caveman diet:\n" +
                "• Cavemen did not all eat the same diet\n" +
                "• Diets come from complicated \ncultural 38 ^"))
        questionForthSection.add(Question(false, "Problems with Caveman diet:\n" +
                "• Costs a lot of money for \nlean meat and 39 ^."))
        questionForthSection.add(Question(false, "• Too much red meat may be unhealthy\n" +
                "• Reliance on meat is bad for the 40 ^"))
    }

    private fun initiateAnswerArrays(){
        firstSectionAnswers = Array(questionFirstSection.size) { "null" }
        secondSectionAnswers = Array(questionSecondSection.size) { "null" }
        thirdSectionAnswers = Array(questionThirdSection.size) { "null" }
        forthSectionAnswers = Array(questionForthSection.size) { "null" }
    }

    private fun initiateFragments(){
        listeningFragmentSectionFirst = ListeningFragment.newInstance('1',"listening_1", questionFirstSection)
        listeningFragmentSectionSecond = ListeningFragment.newInstance('2',"listening_2", questionSecondSection)
        listeningFragmentSectionThird = ListeningFragment.newInstance('3',"listening_3", questionThirdSection)
        listeningFragmentSectionForth = ListeningFragment.newInstance('4',"listening_4", questionForthSection)

    }

    private fun setAndInitiateViewPagerAdapter(){
        listeningActivityViewPagerAdapter = ListeningActivityViewPagerAdapter(supportFragmentManager)
        listeningActivityViewPagerAdapter.addFragment(listeningFragmentSectionFirst, "SECTION 1")
        listeningActivityViewPagerAdapter.addFragment(listeningFragmentSectionSecond, "SECTION 2")
        listeningActivityViewPagerAdapter.addFragment(listeningFragmentSectionThird, "SECTION 3")
        listeningActivityViewPagerAdapter.addFragment(listeningFragmentSectionForth, "SECTION 4")
        listeningViewpager.adapter = listeningActivityViewPagerAdapter
        listeningTabLayout.setupWithViewPager(listeningViewpager)
    }
}