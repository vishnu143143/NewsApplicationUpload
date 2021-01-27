package com.vishnu.happyplaces.activities
//AIzaSyBpy7ZgmtI54aLb2km9tCMdnsS379Gnm98
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishnu.happyplaces.R
import com.vishnu.happyplaces.adapters.HappyPlacesAdapter
import com.vishnu.happyplaces.database.DatabaseHandler
import com.vishnu.happyplaces.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlacesListFromLocalDB()
    }

    private fun getHappyPlacesListFromLocalDB() {

        val dbHandler = DatabaseHandler(this)

        val getHappyPlacesList = dbHandler.getHappyPlacesList()

        // TODO (Step 8: Calling an function which have created for getting list of inserted data from local database
        //  and passing the list to recyclerview to populate in UI.)
        // START
        if (getHappyPlacesList.size > 0) {
            rv_happy_places_list.visibility = View.VISIBLE
            tv_no_records_available.visibility = View.GONE
            setupHappyPlacesRecyclerView(getHappyPlacesList)
        } else {
            rv_happy_places_list.visibility = View.GONE
            tv_no_records_available.visibility = View.VISIBLE
        }
        // END
    }


    private fun setupHappyPlacesRecyclerView(happyPlacesList: ArrayList<HappyPlaceModel>) {

        rv_happy_places_list.layoutManager = LinearLayoutManager(this)
        rv_happy_places_list.setHasFixedSize(true)

        val placesAdapter = HappyPlacesAdapter(this, happyPlacesList)
        rv_happy_places_list.adapter = placesAdapter

        placesAdapter.setOnClickListener(object :
            HappyPlacesAdapter.OnClickListener {
            override fun onClick(position: Int, model: HappyPlaceModel) {
                val intent = Intent(this@MainActivity, HappyPlaceDetailActivity::class.java)

                // TODO (Step 1: Pass the HappyPlaceDetails data model class to the detail activity.)
                // START
                intent.putExtra(EXTRA_PLACE_DETAILS, model) // Passing the complete serializable data class to the detail activity using intent.
                // END
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getHappyPlacesListFromLocalDB()
    }

    companion object {
        private const val ADD_PLACE_ACTIVITY_REQUEST_CODE = 1

        // TODO (Step 2: Create a constant which will be used to put and get the data using intent from one activity to another.)
        // START
        internal const val EXTRA_PLACE_DETAILS = "extra_place_details"
        // END
    }
}
