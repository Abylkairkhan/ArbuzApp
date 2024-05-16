package kz.abyl.arbuz.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.abyl.arbuz.R

@Composable
fun ProductItem(
//    product: Product
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .width(220.dp)
            .height(290.dp)
            .padding(horizontal = 5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.mock_image),
            contentDescription = "Product Image",
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            text = "Клубника Сользхянг",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            maxLines = 2
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            color = Color.LightGray,
            shape = RoundedCornerShape(32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = "3 625 T",
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                )
                IconButton(
                    modifier = Modifier
                        .padding(end = 3.dp)
                        .height(30.dp),
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Favorite",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview(

) {
    ProductItem()
}