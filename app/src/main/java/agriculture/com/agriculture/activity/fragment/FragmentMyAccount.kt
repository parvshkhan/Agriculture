package agriculture.com.agriculture.activity.fragment


import agriculture.com.agriculture.R
import agriculture.com.agriculture.activity.Blur.Blur
import agriculture.com.agriculture.activity.activ.PropertyListSub
import agriculture.com.agriculture.activity.modelresponse.ResponseShowProfile
import agriculture.com.agriculture.activity.modelresponse.ResponseUpdateProfile
import agriculture.com.agriculture.activity.restclint.RestClinnt
import agriculture.com.agriculture.activity.restclint.WikiApiService
import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ContentValues
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File



class FragmentMyAccount : Fragment() {

    val TAKE_PHOTO_REQUEST = 10
    val RESULT_LOAD_IMAGE = 20
    var filePath : File ? =null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


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

    }

    private fun updateProfileInfo(rqId: RequestBody, rqFname: RequestBody, rqImage: RequestBody, rqMobile: RequestBody) {

        val api = RestClinnt.create<WikiApiService>(WikiApiService::class.java)

        api.updateProfile(rqId,rqFname,rqImage,rqMobile).enqueue(object : Callback<ResponseUpdateProfile> {

            override fun onFailure(call: Call<ResponseUpdateProfile>?, t: Throwable?) {

                Toast.makeText(activity,t.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseUpdateProfile>?, response: Response<ResponseUpdateProfile>?) {

                if(response!!.body()!!.isSuccess)
                {
                    Toast.makeText(activity,response.body()!!.message,Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(activity,response.body()!!.message,Toast.LENGTH_LONG).show()
                }
            }
        })
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
                    tvfname.text = response.body()!!.payLoad.firstName + " "+response.body()!!.payLoad.lastName

                    tvEmail.text = response.body()!!.payLoad.email
                    tvphonenumber.text = response.body()!!.payLoad.mobile
                    val blurTransformation = object : Transformation {
                        override fun transform(source: Bitmap): Bitmap {
                            val blurred = Blur.fastblur(context, source, 1)
                            source.recycle()
                            return blurred
                        }

                        override fun key(): String {
                            return "blur()"
                        }
                    }
                    Picasso.get().load(response.body()!!.payLoad.image).placeholder(R.drawable.salman).transform(blurTransformation).into(imageView11);
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
