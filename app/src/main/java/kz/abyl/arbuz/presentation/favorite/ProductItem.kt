package kz.abyl.arbuz.presentation.favorite

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kz.abyl.arbuz.R
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.ui.theme.Gray

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    photo: Photo
) {

    var countInBucket by remember { mutableIntStateOf(photo.countInBucket) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GlideImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = photo.urlRegular,
                contentDescription = "Photo",
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Column {
                    Text(
                        text = photo.altDescription ?: "null",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        maxLines = 2
                    )
                    Text(
                        text = "${photo.width} ₸",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
                GraySurface(
                    onPlusClick = {
                        countInBucket++
                        photo.countInBucket++
                        onPlusClick()
                    },
                    onMinusClick = {
                        countInBucket--
                        photo.countInBucket--
                        onMinusClick()
                    },
                    count = countInBucket
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
                    painter =
                    if (count == 1) painterResource(id = R.drawable.delete_icon)
                        else painterResource(id = R.drawable.remove_icon),
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
        ProductItem(
            {},
            {},
            Photo(
                id = "1",
                width = 100,
                100,
                "White",
                null,
                "Test name",
                10,
                "url",
                "",
                ""
            )
        )
    }
}