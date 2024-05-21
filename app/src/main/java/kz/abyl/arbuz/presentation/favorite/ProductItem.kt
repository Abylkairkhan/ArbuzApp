package kz.abyl.arbuz.presentation.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.abyl.arbuz.R
import kz.abyl.arbuz.ui.theme.Gray
import kz.abyl.arbuz.ui.theme.Green
import kz.abyl.arbuz.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.mock_image),
                contentDescription = "Image"
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Column {
                    Text(
                        text = "Спаржа Arbuz Select",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                    Text(
                        text = "1330 ₸",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
                GraySurface(
                    onPlusClick = { /*TODO*/ },
                    onMinusClick = { /*TODO*/ },
                    count = 3
                )
            }
        }
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
private fun GraySurface(
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    count: Int
) {
    Surface(
        modifier = Modifier
            .width(110.dp)
            .height(35.dp),
        color = Gray,
        shape = RoundedCornerShape(32.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .size(35.dp)
                    .padding(0.dp),
                onClick = onMinusClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.remove_icon),
                    contentDescription = "Remove",
                    tint = Color.Black
                )
            }
            Text(
                text = count.toString(),
                fontFamily = FontFamily(
                    Font(R.font.redhatdisplay_bold),
                ),
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            IconButton(
                modifier = Modifier
                    .size(35.dp)
                    .padding(0.dp),
                onClick = onPlusClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }
    }
}


@Preview
@Composable
fun ProductItemPreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        ProductItem()
    }
}