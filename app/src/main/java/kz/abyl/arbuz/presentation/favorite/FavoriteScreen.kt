package kz.abyl.arbuz.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.abyl.arbuz.R
import kz.abyl.arbuz.presentation.util.ProgressScreen
import kz.abyl.arbuz.ui.theme.Green

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    padding: PaddingValues,
    onBadgeCountChange: (Int) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.onEvent(FavoriteScreenEvent.GetListOfPhotos)
    }

    val state by viewModel.state.collectAsState()
    var count by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(state.photos) {
        count = state.photos.sumOf { it.countInBucket }
        onBadgeCountChange(state.badgeCount)
    }

    if (state.isLoading) {
        ProgressScreen(padding)
    } else {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(padding),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(
                    items = state.photos,
                ) {
                    ProductItem(
                        onPlusClick = {
                            viewModel.onEvent(FavoriteScreenEvent.IncreaseCountOfPhoto(it))
                            count = state.photos.sumOf { it.countInBucket }
//                            viewModel.onEvent(FavoriteScreenEvent.GetPhotoCountFromDatabase)
                        },
                        onMinusClick = {
                            viewModel.onEvent(FavoriteScreenEvent.DecreaseOrDeleteCountOfPhoto(it))
                            count = state.photos.sumOf { it.countInBucket }
//                            viewModel.onEvent(FavoriteScreenEvent.GetPhotoCountFromDatabase)
                        },
                        photo = it
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .width(110.dp)
                    .height(35.dp),
                color = Green,
                shape = RoundedCornerShape(32.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.favorite_icon_fill
                        ),
                        contentDescription = "Heart"
                    )
                    Text(
                        text = count.toString(),
                        fontFamily = FontFamily(
                            Font(R.font.redhatdisplay_bold),
                        ),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}