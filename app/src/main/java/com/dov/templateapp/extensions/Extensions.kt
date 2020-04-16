import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.dov.templateapp.model.Movie
import com.dov.templateapp.view.activities.BaseActivity
import android.content.Intent
import com.dov.templateapp.R


fun BaseActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction().add(frameId, fragment).addToBackStack(null).commit()
}

fun BaseActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment).addToBackStack(null).commit()
}

fun Context.displayToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.displaySnackMessage(
    view: View,
    message: String,
    isWarning: Boolean = false,
    bottomNavigationViewHeight: Int = 0
) {
    val duration: Int = Snackbar.LENGTH_LONG
    val height = bottomNavigationViewHeight
    val snackbar = Snackbar.make(view, message, duration)
    if (isWarning) {
        snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_red_dark))
    }
    val snackbarView = snackbar.view
    val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams
    // height = view.context.resources.getDimension(R.dimen.bottom_bar_height).toInt()
    params.bottomMargin = height
    snackbarView.layoutParams = params
    snackbar.show()
}

fun Context.getDrawableCompat(@DrawableRes res: Int) = ContextCompat.getDrawable(this, res)
fun Context.getColorCompat(@ColorRes res: Int) = ContextCompat.getColor(this, res)

fun Movie.getImageUrl(context: Context): String {
    return context.getString(R.string.image_url) + poster_path
}

fun Movie.getPopularity(context: Context): String {
    return context.getString(R.string.popularity) + popularity.toString()
}

fun Movie.share(context: Context) {
    val shareIntent = Intent(android.content.Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(
        Intent.EXTRA_SUBJECT,
        title
    )
    val shareMessage = overview

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        shareMessage
    )
    context.startActivity(
        Intent.createChooser(
            shareIntent,
            context.getString(R.string.app_name)
        )
    )
}