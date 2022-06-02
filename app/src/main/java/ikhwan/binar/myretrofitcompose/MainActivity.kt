package ikhwan.binar.myretrofitcompose

import android.content.Intent
import android.graphics.drawable.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.myretrofitcompose.model.GetNewsResponseItem
import ikhwan.binar.myretrofitcompose.ui.theme.MyRetrofitComposeTheme
import ikhwan.binar.myretrofitcompose.viewModel.NewsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRetrofitComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel(modelClass = NewsViewModel::class.java)

                    val dataNews by viewModel.dataState.collectAsState()
                    
                    Column(
                    modifier = Modifier.padding(20.dp)) {
                        val context = LocalContext.current
                        Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.fillMaxWidth().weight(9f),
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "News", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.height(50.dp))
                            }
                            Column(modifier = Modifier.fillMaxWidth().weight(1f),
                            horizontalAlignment = Alignment.End,
                            ) {
                                IconButton(modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp),
                                    onClick = {
                                        context.startActivity(Intent(context, StaffActivity::class.java))
                                    }) {
                                    Icon(
                                        Icons.Filled.Person,
                                        "contentDescription",
                                        tint = Color.Black)
                                }
                            }

                        }

                        LazyColumn{
                            items(dataNews){
                                NewsItem(news = it)
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun NewsItem(news : GetNewsResponseItem) {

    val context = LocalContext.current

    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clickable {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("news", news)
                    context.startActivity(intent)
                },
            backgroundColor = Color.LightGray,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = rememberImagePainter(data = news.image),
                    contentDescription = "img",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(130.dp),
                    contentScale = ContentScale.Fit
                )
                Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                    Text(text = news.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = news.createdAt, fontSize = 14.sp)
                    Text(text = news.author, fontSize = 18.sp)

                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyRetrofitComposeTheme {
//        NewsItem()
    }
}