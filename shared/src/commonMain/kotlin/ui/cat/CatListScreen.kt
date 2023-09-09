package ui.cat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.Cat
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import ui.common.ErrorScreen
import ui.common.LoadingScreen

class CatListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { CatListScreenModel() }
        val state by screenModel.state.collectAsState()

        when (val result = state) {
            is CatListScreenModel.State.Initial,
            is CatListScreenModel.State.Loading -> {
                LoadingScreen()
            }

            is CatListScreenModel.State.OnReady -> {
                ListView(
                    items = result.cats,
                    onItemClicked = {
                        navigator.push(CatDetailScreen(it))
                    }
                )
            }

            is CatListScreenModel.State.Error -> {
                ErrorScreen(
                    onRetryClicked = screenModel::fetch
                )
            }
        }
    }

}

@Composable
fun ListView(
    items: List<Cat>,
    onItemClicked: (Cat) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items.let {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                item {
                    Spacer(Modifier.height(0.dp))
                }
                items(it) { cat ->
                    CatListItem(cat) {
                        onItemClicked(cat)
                    }
                }
                item {
                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun CatListItem(
    item: Cat,
    onItemClicked: (Cat) -> Unit,
) {
    Card(
        modifier = Modifier.padding(horizontal = 32.dp)
            .height(150.dp)
            .fillMaxWidth()
            .shadow(8.dp, clip = true, shape = RoundedCornerShape(12.dp))
            .clickable {
                onItemClicked(item)
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            KamelImage(
                modifier = Modifier.fillMaxHeight()
                    .aspectRatio(1.0f),
                contentScale = ContentScale.Crop,
                resource = asyncPainterResource(item.imageUrl),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = item.name
            )
        }
    }
}
