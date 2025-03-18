![banner](./android.png)
# ContentProvider
ContentProvider는 애플리케이션 간 데이터를 안전하게 공유하는 안드로이드 컴포넌트이다.<br/>
내부 데이터베이스(SQLite), 파일, 네트워크 데이터 등을 외부 앱과 공유할 때 사용된다.<br/>
일반적으로 URI(Uniform Resource Identifier)를 통해 데이터를 주고받으며, 권한을 설정하여 보안성을 확보할 수 있다.<br/>
예를 들어, 기기의 연락처 데이터는 `ContactsProvider` 를 통해 접근할 수 있다.<br/>
<br/>
<br/>
<br/>



# ContentProvider 기본 구현
## ContentProvider 생성
`ContentProvider` 를 구현하려면 `android.content.ContentProvider` 클래스를 상속받아 6개의 주요 메서드를 구현해야 한다.<br/>

```kotlin
class MyContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        // ContentProvider 가 생성될 때 호출됨
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        // 데이터 조회 로직
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // 데이터 삽입 로직
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        // 데이터 수정 로직
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // 데이터 삭제 로직
        return 0
    }

    override fun getType(uri: Uri): String? {
        // MIME 타입 반환
        return null
    }
}
```
<br/>
<br/>

## 등록 및 권한 설정
`ContentProvider` 를 사용하려면 매니페스트 파일에 등록해야하며, 데이터 접근을 제어할 수 있다.<br/>

```xml
<!-- 
authorities: 디바이스 전체에서 콘텐츠 프로바이더를 구분하기 위한 식별자이다.
exported: 외부 앱에서 접근에 대한 허용을 설정한다.
permission: 특정 권한이 있는 앱만 접근 가능하도록 설정한다.
grantUriPermissions: 특정 앱이 특정 URI 에 접근할 수 있도록 동적으로 권한을 부여하는 기능으로, 앱이 미리 선언한 권한이 없어도 특정 URI 에 대해 읽기(READ) 또는 쓰기(WRITE) 권한을 일시적으로 부여할 수 있다.
-->
<provider
    android:name=".MyContentProvider"
    android:authorities="com.ymg.contentprovider.provider"
    android:exported="true"
    android:permission="com.example.permission.READ_DATA"
    android:grantUriPermissions="true"/>
```
<br/>
<br/>

## ContentProvider 데이터 접근
### 데이터 조회
```kotlin
// 데이터 조회
fun exampleCheckContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        while (it.moveToNext()) {
            val columnIndex = it.getColumnIndex("column_name")
            val value = it.getString(columnIndex)
            Log.d("ContentProvider", "Value: $value")
        }
    }
}
```
<br/>

### 데이터 삽입
```kotlin
// 데이터 삽입
fun exampleInsertContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val values = ContentValues().apply {
        put("column_name", "sample_value")
    }
    val newUri = context.contentResolver.insert(uri, values)
}
```
<br/>

### 데이터 수정
```kotlin
// 데이터 수정
fun exampleUpdateContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val values = ContentValues().apply {
        put("column_name", "updated_value")
    }
    val updatedRows = context.contentResolver.update(uri, values, "id = ?", arrayOf("1"))
}
```
<br/>

### 데이터 삭제
```kotlin
// 데이터 삭제
fun exampleDeleteContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val deletedRows = context.contentResolver.delete(uri, "id = ?", arrayOf("1"))
}
```