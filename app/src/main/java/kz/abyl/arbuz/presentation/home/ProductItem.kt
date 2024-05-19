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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kz.abyl.arbuz.R
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.ui.theme.Gray
import kz.abyl.arbuz.ui.theme.Green

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    photo: Photo
) {
    
    val color by remember {
        mutableStateOf(Gray)
    }

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
//        GlideImage(
//            model = photo.urlRegular,
//            contentDescription = "Photo"
//        )
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
            color = color,
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
    ProductItem(
        Photo(
            id = "1",
            width = 100,
            100,
            "White",
            null,
            null,
            10,
            "url",
            "",
            ""
        )
    )
}