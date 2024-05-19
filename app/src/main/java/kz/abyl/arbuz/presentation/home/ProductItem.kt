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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.abyl.arbuz.R
import kz.abyl.arbuz.ui.theme.Gray
import kz.abyl.arbuz.ui.theme.Green
import kz.abyl.arbuz.ui.theme.Purple40

@Composable
fun ProductItem(
//    product: Product
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .width(120.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.mock_image),
            contentDescription = "Product Image",
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        )
        Text(
            modifier = Modifier
                .padding(vertical = 6.dp),
            text = "Клубника Сользхянг",
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            color = Gray,
            shape = RoundedCornerShape(32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = "6 293 T",
                    fontFamily = FontFamily(
                        Font(R.font.redhatdisplay_bold),
                    ),
                    fontSize = 12.sp
                )
                IconButton(
                    modifier = Modifier
                        .padding(end = 3.dp)
                        .height(25.dp),
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Favorite",
                        modifier = Modifier.size(35.dp),
                        tint = Green
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