package ui.cat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import domain.Cat
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class CatDetailScreen(private val cat: Cat) : Screen {
    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(32.dp)
            ) {
                KamelImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.9f)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    resource = asyncPainterResource(cat.imageUrl),
                    contentDescription = null,
                )
                Spacer(Modifier.height(16.dp))
                Text(text = cat.name)
                Spacer(Modifier.height(16.dp))
                Text(text = cat.description)
            }
        }
    }
}
