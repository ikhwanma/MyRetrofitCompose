package ikhwan.binar.myretrofitcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ikhwan.binar.myretrofitcompose.model.staff.GetStaffResponseItem
import ikhwan.binar.myretrofitcompose.ui.theme.MyRetrofitComposeTheme

class DetailStaffActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detail = intent.getParcelableExtra<GetStaffResponseItem>("staff") as GetStaffResponseItem
        setContent {
            MyRetrofitComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DetailStaffScreen(staff = detail)
                }
            }
        }
    }
}

@Composable
fun DetailStaffScreen(staff: GetStaffResponseItem) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = staff.image),
            contentDescription = "img",
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        )
        Text(text = staff.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = staff.createdAt, fontSize = 14.sp)
        Text(text = staff.email, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    MyRetrofitComposeTheme {

    }
}