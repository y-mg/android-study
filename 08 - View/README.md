![banner](./android.png)
# View
안드로이드의 뷰(`View`)는 UI 를 구성하는 기본 요소로, 트리 구조로 이루어져 있으며 상위 뷰는 하위 뷰를 포함하는 부모-자식 관계를 형성한다.<br/>
각 뷰는 다양한 작업을 처리하며 화면에 표시되는 UI 를 구성한다.<br/>
뷰가 그려지는 순서는 Top-Down 방식으로, 상위 뷰에서부터 하위 뷰로 내려가며 그려지며, 뷰 계층 구조가 화면에 렌더링되는 순서와 밀접하게 연결된다.<br/>
뷰 계층 구조에서 각 뷰가 순차적으로 수행되는데, 이 과정이 비효율적으로 이루어지면 성능 저하를 초래할 수 있다.<br/>
특히, 화면 렌더링 성능을 높이기 위해서는 뷰의 크기 측정 및 레이아웃 배치, 그리기 과정에 대해 이해하고 최적화하는 것이 중요하다.<br/>
<br/>
<br/>

## View 의 레이아웃 과정
### onMeasure()
각 뷰의 크기(폭과 높이)를 측정하는 단계이다.<br/>
부모 뷰는 자식 뷰가 그려질 공간을 지정해야 하므로, `onMeasure()` 메서드를 호출하여 자식 뷰의 크기를 측정한다.<br/>

```kotlin
override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val width = MeasureSpec.getSize(widthMeasureSpec)
    val height = MeasureSpec.getSize(heightMeasureSpec)
    setMeasuredDimension(width, height)
}
```
<br/>

### onLayout()
측정된 크기에 따라 각 뷰의 위치를 배치하는 단계로, 자식 뷰의 위치를 계산하고 설정한다.<br/>
부모 뷰는 자식 뷰의 크기를 결정한 후 `onLayout()` 메서드를 호출하여 자식 뷰들의 위치를 배치한다.<br/>

```kotlin
override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) { 
    val width = right - left
    val height = bottom - top

    // 자식 뷰의 위치를 배치 
    // children.forEach {
        it.layout(left, top, left + width, top + height)
    }
}
```
<br/>

### onDraw()
`Canvas` 객체를 이용하여 뷰를 실제로 화면에 그리는 단계이다.<br/>
`onDraw()` 뷰의 그래픽을 그릴 수 있으며, 실제로 화면에 보이는 내용을 그리는 역할을 한다.<br/>

```kotlin
@SuppressLint("DrawAllocation")
override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    val paint = Paint()
    paint.color = Color.RED
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
}
```
<br/>
<br/>
<br/>



# Layout
안드로이드의 레이아웃 시스템은 뷰 그룹(`ViewGroup`)을 사용하여 여러 뷰를 배치한다.<br/>
대표적인 레이아웃으로는 `ConstraintLayout`, `LinearLayout`, `FrameLayout`, `RelativeLayout` 등이 있으며, 각각의 레이아웃은 특정한 배치 방법과 성능 특성을 가지고 있다.<br/>
특히, `ConstraintLayout` 은 성능을 최적화하는 데 유리하여 권장되는 레이아웃이다.<br/>
<br/>
<br/>

## 성능 최적화
뷰 계층 구조의 깊이가 깊어질수록 뷰를 검색하고 측정하는 데 시간이 더 소요된다.<br/>
깊이가 깊어지면 측정 및 레이아웃 과정이 복잡해지고, 이로 인해 리소스 소비가 증가하여 메모리 부족 문제를 일으킬 수 있다.<br/>
성능 최적화를 위해서는 복잡한 뷰 계층 구조를 피하고, 가능한 한 `ConstraintLayout` 을 사용하여 하나의 레이아웃에서 다양한 제약 조건을 설정하는 것이 권장된다.<br/>
<br/>
<br/>

## ConstraintLayout
`ConstraintLayout` 은 자식 뷰에 제약 조건(Constraint)을 설정하여 크기와 위치를 지정할 수 있는 레이아웃이다.<br/>
제약 조건을 통해 각 뷰가 서로 어떻게 위치할지에 대한 규칙을 정의할 수 있다.<br/>
이는 `LinearLayout` 에서 제공하는 비율(`layout_weight`) 기능과 `RelativeLayout` 의 상대적인 배치 기능을 모두 사용할 수 있게 하여 매우 유연한 레이아웃을 구성할 수 있다.<br/>

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Click Me"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
<br/>
<br/>

## LinearLayout
`LinearLayout` 은 자식 뷰를 수평(가로) 또는 수직(세로) 방향으로 정렬하는 레이아웃이다.<br/>
`layout_weight` 속성을 사용하여 자식 뷰의 크기를 상대적으로 조정할 수 있다.<br/>
그러나 중첩된 `LinearLayout` 은 성능을 저하시킬 수 있으므로 주의해야 한다.<br/>

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Item 1" />

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="2"
        android:text="Item 2" />

</LinearLayout>
```
<br/>
<br/>

## RelativeLayout
`RelativeLayout` 은 다른 뷰를 기준으로 상대적인 위치와 방향을 지정할 수 있는 레이아웃이다.<br/>
자식 뷰 간의 상대적인 배치를 쉽게 설정할 수 있지만, `ConstraintLayout` 이 제공하는 더 강력한 기능과 성능을 고려하면 현재는 잘 사용되지 않는다.<br/>

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text 1"
        android:layout_alignParentTop="true" />

    <TextView
        android:id="@+id/text2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text 2"
        android:layout_below="@id/text1" />

</RelativeLayout>
```
<br/>
<br/>

## FrameLayout
`FrameLayout` 은 자식 뷰들이 겹쳐지도록 배치될 수 있는 레이아웃이다.<br/>
일반적으로 하나의 자식 뷰를 중심으로 여러 뷰를 겹치게 배치할 때 유용하다.<br/>
예를 들어, 화면에 하나의 뷰가 배경으로 놓이고 그 위에 다른 뷰들이 배치되는 방식이다.<br/>

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <TextView
        android:text="Overlay Text"
        android:layout_gravity="center"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</FrameLayout>
```