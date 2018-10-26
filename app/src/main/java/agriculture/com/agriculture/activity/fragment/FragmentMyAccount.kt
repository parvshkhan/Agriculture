package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.modelresponse.ResponseShowProfile
import agriculture.com.agriculture.activity.modelresponse.ResponseUpdateProfile
import agriculture.com.agriculture.activity.modelresponse.UpdateProfileResponse
import agriculture.com.agriculture.activity.restclint.JRestClient
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.fragment_my_account.*
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.io.File


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FragmentMyAccount : Fragment() {

    val TAKE_PHOTO_REQUEST = 10
    val RESULT_LOAD_IMAGE = 20
    var filePath : File ? = null
    var isEditable = false
    var fName = ""
    var lName = ""
    var mPhone = ""


    override fun onAttach(context: Context?) {
        super.onAttach(CalligraphyContextWrapper.wrap(context))

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        edEmail.isEnabled = false
        setAllAbleEnably(false)


        button2.setOnClickListener {

            if(!isEditable)
            {
                isEditable = !isEditable
                setAllAbleEnably(true)
                button2.setText("EDITABLE DETAILS")
                Toast.makeText(activity,"Now You can update details",Toast.LENGTH_SHORT).show()

            }

        }

        btchangepassword.setOnClickListener {

            Toast.makeText(activity,"Working on it..",Toast.LENGTH_SHORT).show()


        }


        ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE),1)

        getProfileInfo()


        activity!!.findViewById<ImageView>(R.id.imgHamburger).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).openDrawer(Gravity.START)
        }


        activity!!.findViewById<ImageView>(R.id.imgCloseDrawer2).setOnClickListener {
            activity!!.findViewById<DrawerLayout>(R.id.drawer).closeDrawer(Gravity.START)
        }

        (activity!!.findViewById<View>(R.id.drawer)as DrawerLayout).closeDrawer(Gravity.START)



        imageView13.setOnClickListener {
            selectImage()
        }

        btupdateprofileinfo.setOnClickListener {



                if(edFirstName.text.toString().isNotEmpty())
                {
                    fName  = edFirstName.text.toString()
                }
                else if(edLastName.text.toString().isNotEmpty()){
                    lName  = edLastName.text.toString()
                }
                else if(edPhnonenumber.text.toString().isNotEmpty()){
                    mPhone = edPhnonenumber.text.toString()
                }
            var rqImage : RequestBody? = null

                val rqId = RequestBody.create(MediaType.parse("text/plain"), Hawk.get<Int>("id").toString())
                val rqFname = RequestBody.create(MediaType.parse("text/plain"),fName )
                val rqLname = RequestBody.create(MediaType.parse("text/plain"),lName)
                val rqPhoneNum = RequestBody.create(MediaType.parse("text/plain"),mPhone)
            if(filePath!=null)
                 rqImage = RequestBody.create(MediaType.parse("image/*"), filePath)

                val restClient = JRestClient.getClient()

                restClient.updateProfile(rqId,rqFname,rqPhoneNum,rqImage,rqLname).enqueue(object :Callback<ResponseUpdateProfile>{
                    override fun onFailure(call: Call<ResponseUpdateProfile>?, t: Throwable?) {
                        Toast.makeText(activity,t.toString(),Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<ResponseUpdateProfile>?, response: Response<ResponseUpdateProfile>?) {
                        if(response!!.body()!!.isSuccess)
                        {
                            Toast.makeText(activity,response.body()!!.message,Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(activity,response.body()!!.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

    }

    private fun setAllAbleEnably(b: Boolean) {

        edFirstName.isEnabled = b
        edLastName.isEnabled= b
        edPhnonenumber.isEnabled= b
    }


    private fun getProfileInfo() {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.getShowProfile(Hawk.get<Int>("id").toString()).enqueue(object : Callback<ResponseShowProfile> {
            override fun onFailure(call: Call<ResponseShowProfile>?, t: Throwable?) {

                Toast.makeText(activity,t.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseShowProfile>?, response: Response<ResponseShowProfile>?) {

                if(response!!.body()!!.isSuccess)
                {

                    try {
                        tvfname.text = response.body()!!.payLoad.firstName
                        if(response.body()!!.payLoad.lastName == null) {
                            tvedLastName.text = ""
                        }
                        else
                        {
                            tvedLastName.text = response.body()!!.payLoad.lastName
                        }


                        tvEmail.text = response.body()!!.payLoad.email
                        tvphonenumber.text = response.body()!!.payLoad.mobile

                        filePath = File(response.body()!!.payLoad.image)
                        fName = response.body()!!.payLoad.firstName
                        if(response.body()!!.payLoad.lastName!=null)
                        lName = response.body()!!.payLoad.lastName!!
                        mPhone = response.body()!!.payLoad.mobile



                        Picasso.get().load(response.body()!!.payLoad.image).placeholder(R.drawable.salman).into(imageView11);
                    } catch (e: Exception) {
                    }
                }
                else
                {
                    Toast.makeText(activity,response.body()!!.message,Toast.LENGTH_SHORT).show()
                }

            }
        })

    }

    private fun selectImage() {

        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(activity!!)

        builder.setTitle("Add Photo!")

        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {

                launchCamera()


            } else if (options[item] == "Choose from Gallery") {


                launchGallery()

            } else if (options[item] == "Cancel") {

                dialog.dismiss()
            }
        })

        builder.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK
                && requestCode == TAKE_PHOTO_REQUEST) {
            processCapturedPhoto()
        }
        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            var selectedImage = data.getData();
            var filePathColumn = arrayOf(MediaStore.Images.Media.DATA);

            var cursor = activity!!.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            var columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            var photoPath = cursor.getString(columnIndex);
            cursor.close();
            val file = File(photoPath)
            filePath = file
            val uri = Uri.fromFile(file)

            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.getContentResolver(), uri);
            imageView11.background = null
            imageView11.setImageBitmap(bitmap)

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private lateinit var mCurrentPhotoPath: String

    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        val fileUri = activity!!.contentResolver
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(activity!!.packageManager) != null) {
            mCurrentPhotoPath = fileUri.toString()
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, TAKE_PHOTO_REQUEST)
        }
    }

    private fun processCapturedPhoto() {
        val cursor = activity!!.contentResolver.query(Uri.parse(mCurrentPhotoPath),
                Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
                null, null, null)
        cursor.moveToFirst()
        val photoPath = cursor.getString(0)
        cursor.close()
        val file = File(photoPath)
        filePath = file
        val uri = Uri.fromFile(file)

        val bitmap = MediaStore.Images.Media.getBitmap(activity!!.getContentResolver(), uri);
        imageView11.background = null
        imageView11.setImageBitmap(bitmap)

    }



    private fun launchGallery()
    {
        val i = Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(i, RESULT_LOAD_IMAGE)
    }

}
