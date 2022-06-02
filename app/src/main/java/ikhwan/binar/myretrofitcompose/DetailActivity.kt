package ikhwan.binar.myretrofitcompose

import android.os.Bundle
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
import ikhwan.binar.myretrofitcompose.model.GetNewsResponseItem
import ikhwan.binar.myretrofitcompose.ui.theme.MyRetrofitComposeTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailNews =
            intent.getParcelableExtra<GetNewsResponseItem>("news") as GetNewsResponseItem
        setContent {
            MyRetrofitComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DetailScreen(detailNews)
                }
            }
        }
    }
}

@Composable
fun DetailScreen(news : GetNewsResponseItem) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(data = news.image),
            contentDescription = "img",
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        )
        Text(text = news.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = news.createdAt, fontSize = 14.sp)
        Text(text = news.author, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = news.description, fontSize = 18.sp, textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyRetrofitComposeTheme {

    }
}