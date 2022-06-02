package ikhwan.binar.myretrofitcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.myretrofitcompose.model.staff.GetStaffResponseItem
import ikhwan.binar.myretrofitcompose.ui.theme.MyRetrofitComposeTheme
import ikhwan.binar.myretrofitcompose.viewModel.StaffViewModel

@AndroidEntryPoint
class StaffActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRetrofitComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel(modelClass = StaffViewModel::class.java)

                    val dataStaff by viewModel.dataState.collectAsState()


                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(20.dp)) {
                        val context = LocalContext.current
                        Text(text = "Staff", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        LazyColumn {
                            items(dataStaff) {
                                StaffItem(staff = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StaffItem(staff: GetStaffResponseItem) {
    val context = LocalContext.current

    Column(Modifier.padding(20.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clickable {
                    context.startActivity(
                        Intent(context, DetailStaffActivity::class.java).putExtra(
                            "staff",
                            staff
                        )
                    )
                },
            backgroundColor = Color.LightGray,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(data = staff.image),
                    contentDescription = "img",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(130.dp),
                    contentScale = ContentScale.Fit
                )
                Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                    Text(text = staff.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = staff.createdAt, fontSize = 14.sp)
                    Text(text = staff.email, fontSize = 18.sp)

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MyRetrofitComposeTheme {

    }
}