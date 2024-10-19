package com.plcoding.listitemcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.listitemcompose.ui.theme.ListItemComposeTheme

enum class FruitCategory {
    BERRY, TROPICAL, CITRUS, DRUPES, MELON
}

data class Fruit(
    val color: Color,
    val name: String,
    val description: String,
    val category: FruitCategory,
    val isSelected: Boolean = false
)

private val SAMPLE_FRUIT = listOf(
    Fruit(Color(0xFFB22222), "Strawberry", "Sweet and juicy", FruitCategory.BERRY),
    Fruit(Color(0xFFFFD700), "Pineapple", "Tropical and sweet", FruitCategory.TROPICAL),
    Fruit(Color(0xFF66BB6A), "Lime", "Tart and zesty", FruitCategory.CITRUS),
    Fruit(Color(0xFF1E90FF), "Blueberry", "Sweet and juicy", FruitCategory.BERRY),
    Fruit(Color(0xFFFFA500), "Mango", "Tropical and sweet", FruitCategory.TROPICAL),
    Fruit(Color(0xFFFFA07A), "Orange", "Citrusy and sweet", FruitCategory.CITRUS),
    Fruit(Color(0xFF8BC34A), "Kiwi", "Sweet and tart", FruitCategory.BERRY),
    Fruit(Color(0xFF8A2BE2), "Plum", "Sweet and juicy", FruitCategory.DRUPES),
    Fruit(Color(0xFF00CED1), "Coconut", "Creamy and tropical", FruitCategory.TROPICAL),
    Fruit(Color(0xFFFF69B4), "Dragonfruit", "Exotic and sweet", FruitCategory.TROPICAL),

    Fruit(Color(0xFFFFEB3B), "Banana", "Creamy and sweet", FruitCategory.CITRUS),
    Fruit(Color(0xFFFFC0CB), "Peach", "Fuzzy and sweet", FruitCategory.DRUPES),
    Fruit(Color(0xFF4CAF50), "Green Apple", "Crisp and tart", FruitCategory.CITRUS),
    Fruit(Color(0xFFFFC107), "Watermelon", "Juicy and refreshing", FruitCategory.BERRY),
    Fruit(Color(0xFF8A2BE2), "Blackberry", "Rich and sweet", FruitCategory.BERRY),
    Fruit(Color(0xFF00BFFF), "Blue Raspberry", "Sweet and tangy", FruitCategory.BERRY),

    Fruit(Color(0xFFFFA500), "Tangerine", "Citrusy and sweet", FruitCategory.CITRUS),
    Fruit(Color(0xFFFFE4C4), "Nectarine", "Sweet and juicy", FruitCategory.DRUPES),
    Fruit(Color(0xFFF0FFF0), "Honeydew", "Sweet and mild", FruitCategory.MELON),
    Fruit(Color(0xFFF5F5DC), "Casaba Melon", "Mild and sweet", FruitCategory.MELON),
    Fruit(Color(0xFFDC143C), "Cranberry", "Tart and refreshing", FruitCategory.BERRY),

    Fruit(Color(0xFFCD5C5C), "Starfruit", "Tart and sweet", FruitCategory.TROPICAL),
    Fruit(Color(0xFFFFE4B5), "Maracuja", "Tropical and tangy", FruitCategory.TROPICAL),
    Fruit(Color(0xFF8B0000), "Blackcurrant", "Rich and tart", FruitCategory.BERRY),
    Fruit(Color(0xFFFFDAB9), "Lychee", "Sweet and floral", FruitCategory.TROPICAL),
    Fruit(Color(0xFF4CAF50), "Avocado", "Creamy and nutty", FruitCategory.BERRY),

    Fruit(Color(0xFFFFC0CB), "Raspberry", "Sweet and tart", FruitCategory.BERRY),
    Fruit(Color(0xFFD8BFD8), "Olive", "Savory and rich", FruitCategory.DRUPES),
    Fruit(Color(0xFF8A2BE2), "Blue Plum", "Sweet and juicy", FruitCategory.DRUPES),
    Fruit(Color(0xFFFF6347), "Tomato", "Juicy and savory", FruitCategory.CITRUS),
    Fruit(Color(0xFFDABF20), "Figs", "Sweet and chewy", FruitCategory.DRUPES)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListItemComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var fruits by remember {
                        mutableStateOf(SAMPLE_FRUIT)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = innerPadding
                    ) {
                        items(
                            items = fruits,
                            key = { it.name }
                        ) { fruit ->
                            ListItem(
                                headlineContent = {
                                    Text(fruit.name)
                                },
                                supportingContent = {
                                    Text(fruit.description)
                                },
                                leadingContent = {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = null,
                                        tint = fruit.color
                                    )
                                },
                                trailingContent = {
                                    Checkbox(
                                        checked = fruit.isSelected,
                                        onCheckedChange = {
                                            fruits = fruits.map { currentFruit ->
                                                if(currentFruit == fruit) {
                                                    currentFruit.copy(
                                                        isSelected = !currentFruit.isSelected
                                                    )
                                                } else currentFruit
                                            }
                                        }
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        fruits = fruits.map { currentFruit ->
                                            if(currentFruit == fruit) {
                                                currentFruit.copy(
                                                    isSelected = !currentFruit.isSelected
                                                )
                                            } else currentFruit
                                        }
                                    }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}