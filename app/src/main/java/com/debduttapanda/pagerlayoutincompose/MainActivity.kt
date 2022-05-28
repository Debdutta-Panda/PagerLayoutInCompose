package com.debduttapanda.pagerlayoutincompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.debduttapanda.pagerlayoutincompose.ui.theme.PagerLayoutInComposeTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagerLayoutInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text("Pager: Tabs")
                                }
                            )
                        }
                    ){
                        val scope = rememberCoroutineScope()
                        val pagerState = rememberPagerState()
                        val pages = listOf(
                            "Pending",
                            "Done",
                            "All"
                        )
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ){
                            TabRow(
                                selectedTabIndex = pagerState.currentPage,
                                indicator = {tabPositions ->
                                    TabRowDefaults.Indicator(
                                        Modifier
                                            .fillMaxWidth()
                                            .pagerTabIndicatorOffset(pagerState, tabPositions)
                                    )
                                }
                            ){
                                pages.forEachIndexed{ index, title ->
                                    Tab(
                                        text = { Text(title) },
                                        selected = pagerState.currentPage == index,
                                        onClick = {
                                            scope.launch {
                                                pagerState.animateScrollToPage(index)
                                            }
                                        }
                                    )
                                }
                            }
                            HorizontalPager(
                                count = pages.size,
                                modifier = Modifier.fillMaxSize(),
                                state = pagerState
                            ) {page->
                                Text("Page: $page")
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun AllTasks() {
        Text("All Tasks")
    }

    @Composable
    private fun DoneTasks() {
        Text("Completed Tasks")
    }

    @Composable
    private fun PendingTasks() {
        Text("Pending Tasks")
    }
}