![banner](./jetpack.png)
# Jetpack Compose
Jetpack Compose 는 Android UI 를 선언적으로 구성할 수 있도록 하는 UI 툴킷이다.<br/>
<br/>
<br/>

## Text
`Text` 는 화면에 문자열을 출력하는 기본적인 UI 요소다.<br/>
Compose 에서는 `Text` 를 사용하여 다양한 스타일을 적용할 수 있으며, 폰트 크기, 색상, 정렬 등을 설정할 수 있다.<br/>

```kotlin
@Composable
fun ExampleText() {
    Text(
        text = "Hello, Compose!",
        fontSize = 20.sp, // 글자 크기
        fontWeight = FontWeight.Bold, // 글자 굵기
        color = Color.Blue, // 글자 색상
        textAlign = TextAlign.Center // 텍스트 정렬
    )
}
```
<br/>
<br/>

## Button
`Button` 은 사용자가 클릭할 수 있는 UI 요소로, 클릭 이벤트를 처리하는 데 사용된다.

```kotlin
@Composable
fun ExampleButton() {
    Button(
        onClick = { println("Button Clicked") }, // 클릭 이벤트
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue, // 버튼 배경색 지정
            contentColor = Color.White // 버튼 안의 텍스트 색상 변경
        )
    ) {
        Text(text = "Click Me")
    }
}
```
<br/>
<br/>

## Modifier
`Modifier` 는 UI 요소를 꾸미거나 동작을 추가하는 데 사용되는 인터페이스다.<br/>
`Modifier` 를 통해 크기, 패딩, 배경색, 클릭 이벤트 등을 조정할 수 있다.<br/>

```kotlin
@Composable
fun ExampleModifier() {
    Text(
        text = "Hello, Modifier!",
        modifier = Modifier
            .padding(16.dp) // 패딩 추가
            .background(Color.Gray) // 배경색 추가
            .clickable {
                println("Text Clicked")
            } // 클릭 이벤트 추가
    )
}
```
<br/>
<br/>

## Surface
`Surface` 는 Compose 에서 UI 요소들의 배경과 스타일을 정의하는 컨테이너 역할을 한다.<br/>
레이아웃의 배경색을 설정하거나 그림자 효과를 줄 때 사용할 수 있다.<br/>

```kotlin
@Composable
fun ExampleSurface() {
    Surface(
        color = Color.LightGray, // Surface 의 배경색
        shape = RoundedCornerShape(8.dp), // 모서리를 둥글게 처리
        shadowElevation = 4.dp, // 그림자 효과 추가
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Surface Example",
            modifier = Modifier.padding(16.dp)
        )
    }
}
```
<br/>
<br/>

## Box
`Box` 는 Z축(Stack) 방향으로 UI 요소를 배치하는 컨테이너로, 자식 요소들을 겹쳐서 배치할 때 사용된다.<br/>

```kotlin
@Composable
fun ExampleBox() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
    ) {
        Text(
            text = "Inside Box",
            color = Color.White,
            modifier = Modifier.align(
                Alignment.Center // 중앙 정렬
            )
        )
    }
}
```
<br/>
<br/>

## Row
`Row` 는 가로 방향(수평)으로 UI 요소를 배치하는 컨테이너이다.<br/>

```kotlin
@Composable
fun ExampleRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // 정렬 방식을 지정
        verticalAlignment = Alignment.CenterVertically // 자식 요소의 세로 정렬을 조정
    ) {
        Text(
            text = "Start", 
            color = Color.Black
        )
        Text(
            text = "Center", 
            color = Color.Blue
        )
        Text(
            text = "End", 
            color = Color.Red
        )
    }
}
```
<br/>
<br/>

## Column
`Column` 은 세로 방향(수직)으로 UI 요소를 배치하는 컨테이너이다.<br/>

```kotlin
@Composable
fun ExampleColumn() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly, // 정렬 방식을 지정
        horizontalAlignment = Alignment.CenterHorizontally // 자식 요소의 가로 정렬을 조정
    ) {
        Text(
            text = "Item 1",
            color = Color.Black
        )
        Text(
            text = "Item 2",
            color = Color.Blue
        )
        Text(
            text = "Item 3",
            color = Color.Red
        )
    }
}
```
<br/>
<br/>

## BoxWithConstraints
`BoxWithConstraints` 는 `Box` 와 유사하지만, UI 요소의 크기 및 제약 조건을 내부에서 참조할 수 있다.<br/>
`maxWidth`, `maxHeight`, `minWidth`, `minHeight` 등의 값을 사용하여 크기 조건을 확인할 수 있어서 화면 크기에 따라 동적으로 다른 UI 를 렌더링할 때 유용하다.<br/>

```kotlin
@Composable
fun ExampleBoxWithConstraints() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        if (maxWidth < 400.dp) {
            Text(
                text = "작은 화면",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Text(
                text = "큰 화면",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
```
<br/>
<br/>

## Image
`Image` 는 이미지 리소스를 화면에 표시한다.<br/>

```kotlin
@Composable
fun ExampleImage() {
    Image(
        painter = painterResource( // 로컬 리소스를 로드
            id = android.R.drawable.stat_notify_sdcard
        ),
        contentDescription = "Sample Image",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp)), // 둥근 모서리로 스타일링
        contentScale = ContentScale.Crop // 이미지 크기 조정 방식을 지정
    )
}
```
<br/>
<br/>

## NetworkImage
NetworkImage 는 URL 을 통해 이미지를 로드하며, Coil 등의 라이브러리를 사용하여 구현할 수 있다.<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
coil = "2.7.0"

[libraries]
coil-compose = { group = "io.coil-kt", name = "coil-compose", version.ref = "coil" }
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.coil.compose)
}
```
<br/>

### 예제 코드
```kotlin
@Composable
fun ExampleNetworkImage() {
    AsyncImage(
        model = "https://assets-prd.ignimgs.com/2024/06/12/new-mh-wilds-blog-1718169422801.jpg",
        contentDescription = "Network Image",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}
```
<br/>
<br/>

## Card
`Card` 는 Compose UI 에서 카드 형태의 컨테이너를 제공하는 컴포넌트이다.<br/>
`Card` 를 사용하면 그림자, 테두리, 배경 등을 손쉽게 추가할 수 있으며, UI 요소를 그룹화하여 시각적인 구분을 제공할 수 있다.<br/>

```kotlin
@Composable
fun ExampleCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue // 카드의 배경색을 설정할 수 있음
        ),
        shape = RoundedCornerShape(8.dp), // 모서리를 둥글게 처리
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp // 그림자의 높이를 조절하여 입체감을 조정할 수 있음
        )
    ) {
        Text(
            text = "Hello, Card!",
            modifier = Modifier.padding(16.dp)
        )
    }
}
```
<br/>
<br/>

## CheckBox
`CheckBox` 는 사용자가 체크할 수 있는 UI 요소로, 상태를 관리하며 선택 여부를 나타낸다.<br/>

```kotlin
@Composable
fun ExampleCheckBox() {
    var checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked, // 체크박스의 현재 상태
            onCheckedChange = { // 체크 상태 변경 시 호출되는 콜백 함수
                checked = it
            }
        )
        Text(
            text = if (checked) {
                "Checked"
            } else {
                "Unchecked"
            }
        )
    }
}
```
<br/>
<br/>

## TextField
`TextField` 는 사용자가 텍스트를 입력할 수 있는 UI 요소이다.<br/>

```kotlin
@Composable
fun ExampleTextField() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text, // 입력된 텍스트 값
        onValueChange = { // 텍스트 변경 시 호출되는 콜백 함수
            text = it
        },
        label = { // 입력 필드 위에 표시할 라벨
            Text("Enter text")
        }
    )
}
```
<br/>
<br/>

## TopAppBar
`TopAppBar` 는 상단에 위치하는 앱 바(AppBar)를 제공하는 컴포넌트이다.<br/>
앱의 제목을 표시하거나 네비게이션 버튼을 추가할 수 있다.<br/>

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleTopAppBar() {
    TopAppBar(
        title = { // 앱 바의 제목을 설정
            Text("My App")
        },
        navigationIcon = { // 좌측에 배치할 네비게이션 아이콘
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Menu"
                )
            }
        },
        actions = { // 우측에 배치할 액션 버튼을 설정
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}
```
<br/>
<br/>

## Scaffold
`Scaffold` 는 `TopAppBar`, `BottomAppBar`, `FloatingActionButton` 등의 기본적인 앱 레이아웃을 제공하는 컨테이너이다.<br/>

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleScaffold() {
    Scaffold(
        topBar = { // 상단 앱 바를 설정
            TopAppBar(
                title = {
                    Text("Scaffold Example")
                }
            )
        },
        floatingActionButton = { // 플로팅 액션 버튼
            FloatingActionButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { paddingValues ->
        // 메인 컨텐츠 영역
        Text(
            "Content here",
            modifier = Modifier.padding(paddingValues)
        )
    }
}
```
<br/>
<br/>

## LazyColumn
`LazyColumn `은 성능을 고려한 리스트 컴포넌트로, 화면에 필요한 항목만 렌더링하여 성능을 최적화한다.<br/>
`LazyColumn` 에서는 `items` 에 `key` 를 설정하여 각 아이템의 고유 식별자를 제공함으로써, 불필요한 리컴포지션을 방지할 수 있다.<br/>

```kotlin
private data class Example(
    val id: Int,
    val name: String
)

@Composable
fun ExampleLazyColumn() {
    val items = List(100) {
        Example(
            id = it,
            name = "$it"
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = items,
            key = {
                it.id
            }
        ) { item ->
            Text(
                text = item.name,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
```
<br/>
<br/>

## ConstraintLayout
`ConstraintLayout` 은 Jetpack Compose 에서 복잡한 UI 배치를 보다 유연하게 구성할 수 있도록 해주는 레이아웃 시스템이다.<br/>
기존 XML 기반의 `ConstraintLayout` 과 유사한 개념을 유지하면서도, `Modifier` 와 `ConstraintSet` 을 사용하여 더욱 직관적인 UI 배치를 가능하게 한다.<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-constraintlayout-compose = "1.1.1"

[libraries]
androidx-constraintlayout-compose = { group = "androidx.constraintlayout", name = "constraintlayout-compose", version.ref = "androidx-constraintlayout-compose"}
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.androidx.constraintlayout.compose)
}
```
<br/>

### 예제 코드
```kotlin
@Composable
fun ExampleConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (text, button) = createRefs()

        Text(
            text = "Hello, Compose!",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = {

            },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(text.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("클릭")
        }
    }
}
```
<br/>
<br/>

## ConstraintSet
`ConstraintSet` 은 `ConstraintLayout` 내에서 뷰들의 위치를 동적으로 설정할 수 있도록 도와준다.<br/>
레이아웃을 유연하게 정의하고, 상태 변경 시 새로운 제약 조건을 적용할 수 있다.<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-constraintlayout-compose = "1.1.1"

[libraries]
androidx-constraintlayout-compose = { group = "androidx.constraintlayout", name = "constraintlayout-compose", version.ref = "androidx-constraintlayout-compose"}
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.androidx.constraintlayout.compose)
}
```
<br/>

### 예제 코드
```kotlin
@Composable
fun ExampleConstraintSet() {
    val constraints = ConstraintSet {
        val text = createRefFor("text")
        val button = createRefFor("button")

        constrain(text) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(button) {
            top.linkTo(text.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello, Compose!",
            modifier = Modifier.layoutId("text")
        )

        Button(
            onClick = { 
                
            },
            modifier = Modifier.layoutId("button")
        ) {
            Text("클릭")
        }
    }
}
```
<br/>
<br/>

## AdvancedConstraintLayout
`AdvancedConstraintLayout` 은 `ConstraintLayout` 의 확장된 기능을 활용하여 더 복잡한 UI 를 구성하는 기법이다.<br/>
`Barrier`, `Chain`, `Guideline` 등의 기능을 적극적으로 활용하여 다양한 배치 방식 구현이 가능하다.<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-constraintlayout-compose = "1.1.1"

[libraries]
androidx-constraintlayout-compose = { group = "androidx.constraintlayout", name = "constraintlayout-compose", version.ref = "androidx-constraintlayout-compose"}
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.androidx.constraintlayout.compose)
}
```
<br/>

### 예제 코드
```kotlin
@Composable
fun ExampleAdvancedConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val guideline = createGuidelineFromStart(0.3f) // 특정 위치에 가상의 가이드를 추가하여 정렬
        val text = createRef()

        Text(
            text = "Guideline Example",
            modifier = Modifier.constrainAs(text) {
                start.linkTo(guideline)
                top.linkTo(parent.top, margin = 16.dp)
            }
        )
    }
}
```
<br/>
<br/>

## Canvas
`Canvas` 는 Jetpack Compose 에서 커스텀 그래픽을 그릴 수 있다.<br/>
`drawCircle()`, `drawRect()`, `drawPath()` 등 다양한 그리기 함수를 사용하여 직접 도형, 선, 텍스트 등을 그릴 수 있다.<br/>

```kotlin
@Composable
fun ExampleCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color.Red,
            radius = 100f,
            center = Offset(size.width / 2, size.height / 2)
        )
    }
}
```
<br/>
<br/>

## Dialog
`Dialog` 는 Compose 에서 팝업 형태의 UI를 띄울 수 있는 컴포넌트이다.<br/>
`AlertDialog` 를 활용하여 기본적인 다이얼로그를 구현할 수 있다.<br/>

```kotlin
@Composable
fun ExampleDialog(
    showDialog: Boolean, 
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { 
                Text(text = "제목") 
            },
            text = { 
                Text("다이얼로그 내용입니다.") 
            },
            confirmButton = {
                Button(onClick = onDismiss) { 
                    Text("확인") 
                }
            }
        )
    }
}
```
<br/>
<br/>

## CustomDialog
CustomDialog 는 `Dialog` 를 커스텀하여 사용할 때 활용된다.<br/>

```kotlin
@Composable
fun ExampleCustomDialog(
    showDialog: Boolean, 
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    Color.White, 
                    shape = RoundedCornerShape(8.dp
                    )
                )
                .padding(16.dp)
        ) {
            Column {
                Text(text = "Custom Dialog")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onDismiss
                ) { 
                    Text("닫기") 
                }
            }
        }
    }
}
```
<br/>
<br/>

## DropdownMenu
`DropdownMenu` 는 클릭 시 옵션을 선택할 수 있는 드롭다운 메뉴를 제공한다.<br/>

```kotlin
@Composable
fun ExampleDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        "Option 1", 
        "Option 2", 
        "Option 3"
    )

    Box {
        Button(
            onClick = { 
                expanded = true
            }
        ) {
            Text("메뉴 열기")
        }

        DropdownMenu(
            expanded = expanded, 
            onDismissRequest = { 
                expanded = false 
            }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { 
                        Text(item) 
                    }, 
                    onClick = { 
                        expanded = false
                    }
                )
            }
        }
    }
}
```
<br/>
<br/>

## Snackbar
`Snackbar` 는 사용자에게 간결한 피드백 메시지를 표시하는 컴포넌트이다.<br/>
일반적으로 화면 하단에 나타나며, 자동으로 사라지거나 사용자가 액션 버튼을 눌러 반응할 수 있다.<br/>
안드로이드 컴포즈에서는 `SnackbarHost` 와 `SnackbarHostState` 를 사용하여 `Snackbar` 를 관리한다.<br/>

```kotlin
@Composable
fun ExampleSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            scope.launch {
                snackbarHostState.showSnackbar("Snackbar 메시지")
            }
        }) {
            Text("Show Snackbar")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
```
<br/>
<br/>

## BottomAppBar
`BottomAppBar` 는 화면 하단에 고정된 앱 바로, 네비게이션 버튼이나 주요 액션을 배치할 수 있다.<br/>

```kotlin
@Composable
fun ExampleBottomAppBar() {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                IconButton(
                    onClick = { 
                        
                    }
                ) {
                    Icon(
                        Icons.Default.Home, 
                        contentDescription = "홈"
                    )
                }
                IconButton(
                    onClick = { 
                        
                    }
                ) {
                    Icon(
                        Icons.Default.Favorite, 
                        contentDescription = "즐겨찾기"
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Text("BottomAppBar 예제")
        }
    }
}
```
<br/>
<br/>

## CompositionLocal
`CompositionLocal` 은 컴포즈에서 계층적으로 데이터를 전달하는 방법 중 하나이다.<br/>
예를 들어 테마나 설정 정보를 전역적으로 관리하는 데 사용할 수 있다.<br/>

```kotlin
val LocalCounter = compositionLocalOf { 0 }

@Composable
fun ParentComponent() {
    CompositionLocalProvider(LocalCounter provides 5) {
        ChildComponent()
    }
}

@Composable
fun ChildComponent() {
    Text(
        "Counter: ${LocalCounter.current}"
    )
}
```
<br/>
<br/>

## Theme
컴포즈에서는 `MaterialTheme` 을 사용하여 테마를 정의할 수 있다.<br/>
색상, 타이포그래피, 셰이프를 설정하여 전체 앱의 스타일을 일관되게 유지할 수 있다.<br/>

```kotlin
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun ComposeComponentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
        darkTheme -> {
            DarkColorScheme
        }
        else -> {
            LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```
<br/>
<br/>

## Navigation
`Navigation` 은 앱 내에서 화면 간의 이동을 관리하는 컴포넌트로, 컴포즈에서 `Navigation` 은 화면 간의 전환을 쉽게 구현할 수 있도록 도와준다.<br/>
이를 통해 화면 전환을 선언적으로 처리하며, `NavHost`, `NavController`, `NavBackStackEntry` 등을 활용하여 화면 전환 및 상태 관리가 가능하다.<br/>

<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-navigation-compose = "2.8.9"

[libraries]
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "androidx-navigation-compose"}
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.androidx.navigation.compose)
}
```
<br/>

### 예제 코드
```kotlin
@Composable
fun ExampleNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailScreen() }
    }
}

@Composable
private fun HomeScreen(navController: NavController) {
    Column {
        Text(text = "홈 화면")
        Button(
            onClick = { 
                navController.navigate("details") 
            }
        ) {
            Text("상세 화면으로 이동")
        }
    }
}

@Composable
private fun DetailScreen() {
    Text("상세 화면")
}
```