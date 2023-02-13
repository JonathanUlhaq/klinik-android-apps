package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.PagerDot
import com.example.aplikasiklinik.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@Composable
@OptIn(ExperimentalPagerApi::class)
fun WidgetAnnouncement(state: PagerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .height(110.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.announce_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.onPrimary.copy(0.85F))
        )

        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            HorizontalPager(
                count = 2,
                state = state) {
                AnnounceItem()
            }
            Spacer(modifier = Modifier.height(7.dp))
            PagerDot(
                index = 2, currentIndex = state.currentPage,
                color = MaterialTheme.colors.onSurface.copy(0.6F)
            )
        }
    }
}