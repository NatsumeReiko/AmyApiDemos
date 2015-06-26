/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.text;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.apis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnicodeTest extends Activity {
    String[] values = new String[] {
            "\u30b9\u30dd\u30fc\u30c6\u30a3\u30b7\u30e3\u30c4\u30fb\u30c1\u30a7\u30c3\u30af\u30d7\u30ea\u30fc\u30c4\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30e9\u30d5\u30eb\u30ab\u30b8\u30e5\u30a2\u30eb\u30b3\u30fc\u30c7\u30fb\u30d5\u30ed\u30f3\u30c8\u30ea\u30dc\u30f3\u30c9\u30c3\u30c8\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30af\u30fc\u30eb\u30bb\u30fc\u30e9\u30fc\u30b3\u30fc\u30c7\u30fb\u30ce\u30fc\u30b9\u30ea\u30fc\u30d6\u30bb\u30fc\u30e9\u30fc\u30e9\u30a4\u30f3\u30b9\u30ab\u30fc\u30c8\u30fb\u9ed2",
            "\u30b7\u30f3\u30d7\u30eb\u30dc\u30c8\u30e0\u30b9\u30fb\u30e9\u30a4\u30c8\u30d6\u30eb\u30fc",
            "\u7d2b\u967d\u82b1\u67c4\u30b9\u30ab\u30fc\u30c8\u30fb\u30d4\u30f3\u30af",
            "\u7d2b\u967d\u82b1\u67c4\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30eb\u30fc",
            "\u30d5\u30ea\u30eb\u30ec\u30fc\u30b9\u30b9\u30af\u30fc\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30eb\u30d3\u30fc",
            "\u30d5\u30ea\u30eb\u30ec\u30fc\u30b9\u30b9\u30af\u30fc\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30b5\u30d5\u30a1\u30a4\u30a2",
            "\u30d5\u30ea\u30eb\u30ec\u30fc\u30b9\u30b9\u30af\u30fc\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30c0\u30fc\u30af",
            "\u30ad\u30e5\u30fc\u30c8\u306a\u30d4\u30a2\u30ce\u67c4\u30d5\u30ec\u30a2\u30b9\u30ab\u30fc\u30c8\u30fb\u30ef\u30a4\u30f3",
            "\u30ad\u30e5\u30fc\u30c8\u306a\u30d4\u30a2\u30ce\u67c4\u30d5\u30ec\u30a2\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30e9\u30c3\u30af",
            "\u6625\u8272\u3075\u308f\u3075\u308f\u82b1\u67c4\u30d5\u30ec\u30a2\u30b9\u30ab\u30fc\u30c8",
            "\u30b4\u30b7\u30c3\u30af\u30d4\u30a2\u30ce\u30b3\u30fc\u30c7\u30fb\u30d4\u30a2\u30ce\u67c4\u30d5\u30ec\u30a2\u30b9\u30ab\u30fc\u30c8\u30fb\u30db\u30ef\u30a4\u30c8",
            "\u304a\u5b22\u3055\u307e\u98a8\u8896\u900f\u3051\u7de8\u307f\u4e0a\u3052\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30e9\u30c3\u30af",
            "\u30ac\u30fc\u30ea\u30fc\u30d5\u30e9\u30ef\u30fc\u30ec\u30fc\u30b9\u30b9\u30ab\u30fc\u30c8\u30fb\u30d4\u30f3\u30af",
            "\u304d\u3085\u3093\u304b\u308f\u30c1\u30a7\u30ea\u30fc\u30b9\u30ab\u30fc\u30c8\u30fb\u30a4\u30a8\u30ed\u30fc",
            "\u304d\u3085\u3093\u304b\u308f\u30c1\u30a7\u30ea\u30fc\u30b9\u30ab\u30fc\u30c8\u30fb\u30db\u30ef\u30a4\u30c8",
            "\u30ac\u30fc\u30ea\u30fc\u30d5\u30e9\u30ef\u30fc\u30ec\u30fc\u30b9\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30eb\u30fc",
            "\u30bd\u30c3\u30af\u30b9\u30ac\u30fc\u30bf\u30fc\u4ed8\u304d\u9ed2\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30c6\u3099\u30d2\u3099\u30eb\u30ab\u3099\u30fc\u30eb\u30b3\u30fc\u30c6\u3099\u30fb\u30a2\u30af\u30bb\u8d64\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30b4\u30b7\u30c3\u30af\u30d1\u30f3\u30af\u30fb\u30d9\u30eb\u30c8\u3064\u304d\u30d1\u30f3\u30c4",
            "\u30b5\u30a4\u30c9\u30d9\u30eb\u30c8\u3064\u304d\u30d1\u30f3\u30c4\u30fb\u30db\u30ef\u30a4\u30c8",
            "\u30b5\u30a4\u30c9\u30d9\u30eb\u30c8\u3064\u304d\u30d1\u30f3\u30c4\u30fb\u30ab\u30fc\u30b4",
            "\u8d64\u30d1\u30f3\u30af\u30fb\u30d1\u30f3\u30af\u30ed\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u8170\u5dfb\u304d\u30ab\u30fc\u30c7\u4ed8\u304d\u30b9\u30ab\u30fc\u30c8",
            "\u30d1\u30b9\u30c6\u30eb\u30a4\u30a8\u30ed\u30fc\u4e8c\u6bb5\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30d1\u30b9\u30c6\u30eb\u30d4\u30f3\u30af\u4e8c\u6bb5\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30d1\u30b9\u30c6\u30eb\u30d6\u30eb\u30fc\u4e8c\u6bb5\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30ad\u30e3\u30c3\u30c8\u30c1\u30a7\u30c3\u30af\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30ec\u30e2\u30f3\u67c4\u8d64\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30ec\u30e2\u30f3\u67c4\u6c34\u8272\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u82b1\u67c4\u30d1\u30fc\u30d7\u30eb\u30df\u30cb\u30b9\u30ab",
            "\u30c1\u30a7\u30c3\u30af\u900f\u3051\u30b9\u30ab\u30fc\u30c8\u30fb\u30d1\u30b9\u30c6\u30eb\u30a4\u30a8\u30ed\u30fc",
            "\u30c1\u30a7\u30c3\u30af\u900f\u3051\u30b9\u30ab\u30fc\u30c8\u30fb\u30d1\u30b9\u30c6\u30eb\u30d4\u30f3\u30af",
            "\u30c1\u30a7\u30c3\u30af\u900f\u3051\u30b9\u30ab\u30fc\u30c8\u30fb\u30d1\u30b9\u30c6\u30eb\u30d6\u30eb\u30fc",
            "\u30d5\u30e9\u30ef\u30fc\u30ac\u30fc\u30ea\u30fc\u30b3\u30fc\u30c7\u30fb\u82b1\u67c4\u30d4\u30f3\u30af\u30df\u30cb\u30b9\u30ab",
            "\u30ab\u30e9\u30d5\u30eb\u5236\u670d\u30b3\u30fc\u30c7\u30fb\u30ab\u30e9\u30d5\u30eb\u30d4\u30f3\u30af\u30d5\u30e9\u30ef\u30fc\u30df\u30cb\u30b9\u30ab",
            "\u30d5\u30eb\u30fc\u30c4\u30ab\u3099\u30fc\u30eb\u30b3\u30fc\u30c6\u3099\u30fb\u30ec\u30e2\u30f3\u67c4\u9ed2\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30e2\u30ce\u30af\u30ed\u30cf\u30fc\u30c8\u67c4\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u900f\u3051\u611f\u30db\u30ef\u30a4\u30c8\u30b9\u30ab\u30fc\u30c8",
            "\u30d5\u3099\u30e9\u30c3\u30af\u30ed\u30c3\u30af\u30b3\u30fc\u30c6\u3099\u30fb\u30bf\u30a4\u30c4\u4ed8\u304d\u9ed2\u30c7\u30cb\u30e0\u30d1\u30f3\u30c4",
            "\u30db\u309a\u30c3\u30d5\u309a\u30b9\u30ab\u30eb\u30b3\u30fc\u30c6\u3099\u30fb\u30b9\u30bf\u30c3\u30ba\u4ed8\u304d\u30ec\u30b6\u30fc\u30df\u30cb\u30b9\u30ab",
            "\u7de8\u307f\u4e0a\u3052\u30d1\u30f3\u30c4\u30fb\u30a4\u30a8\u30ed\u30fc",
            "\u7de8\u307f\u4e0a\u3052\u30d1\u30f3\u30c4\u30fb\u30d6\u30eb\u30fc",
            "\u3055\u308f\u3084\u304b\u30b9\u30dd\u30fc\u30c6\u30a3\u7cfb\u30d1\u30f3\u30c4",
            "\u82b1\u67c4\u30b7\u30f3\u30d7\u30eb\u30dc\u30fc\u30c0\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u30dd\u30c3\u30d7\u30b9\u30c8\u30ed\u30d9\u30ea\u30fc\u30b3\u30fc\u30c7\u30fb\u30ec\u30fc\u30b9\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u30d1\u30b9\u30c6\u30eb\u30ab\u30b8\u30e5\u30a2\u30eb\u30b3\u30fc\u30c7\u30fb\u30ec\u30fc\u30b9\u67c4\u6298\u308a\u8fd4\u3057\u30d6\u30eb\u30fc\u30d1\u30f3\u30c4",
            "\u30c1\u30a7\u30c3\u30af\u67c4\u30d5\u30ec\u30c3\u30d4\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u30b4\u30b9\u30ed\u30ea\u30d0\u30c3\u30b9\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30db\u30ef\u30a4\u30c8\u30d5\u30e9\u30ef\u30fc\u30da\u30a4\u30f3\u30c8\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d6\u30e9\u30c3\u30af\u30d5\u30e9\u30ef\u30fc\u30da\u30a4\u30f3\u30c8\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30b7\u30c3\u30af\u98a8\u304a\u5b22\u30b3\u30fc\u30c7\u30fb\u30ea\u30dc\u30f3\u4ed8\u304d\u3075\u308a\u3075\u308a\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u8ff7\u5f69\u67c4\u30b5\u30eb\u30a8\u30eb\u30d1\u30f3\u30c4",
            "\u5b87\u5b99\u67c4\u30b5\u30eb\u30a8\u30eb\u30d1\u30f3\u30c4",
            "\u30ec\u30c3\u30c9\u30e9\u30a4\u30f3\u591a\u6bb5\u30d5\u30ea\u30eb\u9ed2\u30b9\u30ab\u30fc\u30c8",
            "\u8170\u5dfb\u304d\u30c1\u30a7\u30c3\u30af\u4ed8\u304d\u30e2\u30ce\u30af\u30ed\u30dc\u30fc\u30c0\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u6625\u30b9\u30dd\u30fc\u30c6\u30a3\u30b3\u30fc\u30c7\u30fb\u4e8c\u6bb5\u30d5\u30ea\u30eb\u30c7\u30cb\u30e0\u30b9\u30ab\u30fc\u30c8",
            "\u82b1\u67c4\u30df\u30cb\u30b9\u30ab\u30fc\u30c8\u30fb\u30d1\u30fc\u30d7\u30eb",
            "\u3086\u3081\u304b\u308f\u98a8\u30d4\u30f3\u30af\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30d6\u30e9\u30c3\u30af\u30b9\u30c8\u30e9\u30a4\u30d7\u30b9\u30ad\u30cb\u30fc",
            "\u30d4\u30f3\u30af\u30b9\u30c8\u30e9\u30a4\u30d7\u30b9\u30ad\u30cb\u30fc",
            "\u30d9\u30eb\u30c8\u4ed8\u304d\u304d\u308c\u3044\u3081\u82b1\u67c4\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30e2\u30ce\u30c8\u30fc\u30f3\u30b9\u30c8\u30e9\u30a4\u30d7\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30e2\u30d5\u30e9\u67c4\u30b9\u30bf\u30c3\u30ba\u4ed8\u304d\u30bf\u30a4\u30c8\u30b9\u30ab\u30fc\u30c8",
            "\u30eb\u30fc\u30ba\u5236\u670d\u30fb\u30b0\u30ec\u30fc\u30ab\u30fc\u30c7\u8170\u5dfb\u304d\u30b9\u30ab\u30fc\u30c8",
            "\u30eb\u30fc\u30ba\u5236\u670d\u30fb\u30db\u30ef\u30a4\u30c8\u30ab\u30fc\u30c7\u8170\u5dfb\u304d\u30b9\u30ab\u30fc\u30c8",
            "\u67c4\u3064\u304d\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u9ec4\u8272",
            "\u67c4\u3064\u304d\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u6c34\u8272",
            "\u6bb5\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30d4\u30f3\u30af",
            "\u30d9\u30eb\u30c8\u3064\u304d\u30d1\u30f3\u30c4\u30fb\u3048\u3093\u3058",
            "\u30d9\u30eb\u30c8\u3064\u304d\u30d1\u30f3\u30c4\u30fb\u30e2\u30b9\u30b0\u30ea\u30fc\u30f3",
            "\u8d64\u30b9\u30bf\u30b8\u30e3\u30f3\u30fb\u6c34\u8272\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u3075\u308f\u3075\u308f\u7cfb\u30fb\u30d4\u30f3\u30af\u30bf\u30a4\u30c4\u3064\u304d\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u30e2\u30c6\u5236\u670d\u30fb\u30d6\u30eb\u30fc\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u3075\u3093\u308f\u308a\u30c1\u30e5\u30cb\u30c3\u30af\u30b3\u30fc\u30c7\u30fb\u591a\u6bb5\u30b9\u30ab\u30fc\u30c8",
            "\u3075\u3093\u308f\u308a\u30b7\u30d5\u30a9\u30f3\u30b3\u30fc\u30c7\u30fb\u82b1\u67c4\u30b9\u30ab\u30fc\u30c8",
            "\u30aa\u30c8\u30ca\u30b5\u30de\u30fc\u30b3\u30fc\u30c7\u30fb\u30af\u30e9\u30c3\u30b7\u30e5\u30b5\u30de\u30fc\u30c7\u30cb\u30e0",
            "\u30a2\u30b7\u30f3\u30e1\u30c8\u30ea\u30fc\u30d1\u30f3\u30c4\u30fb\u30b0\u30ea\u30fc\u30f3",
            "\u304a\u3067\u304b\u3051\u30a2\u30af\u30a2\u30b3\u30fc\u30c7\u30fb\u304f\u3057\u3085\u3063\u3068\u4e03\u90e8\u4e08\u30d1\u30f3\u30c4",
            "\u30d1\u30b9\u30c6\u30eb\u8ff7\u5f69\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30bc\u30d6\u30e9\u67c4\u30d9\u30eb\u30c8\u4ed8\u304d\u30ec\u30c3\u30c9\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30ec\u30fc\u30b9\u30c7\u30cb\u30e0\u30d9\u30fc\u30b7\u30c3\u30af\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30ec\u30fc\u30b9\u30c7\u30cb\u30e0\u30aa\u30c8\u30ca\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u304a\u3057\u3068\u3084\u304b\u30ea\u30dc\u30f3\u30b9\u30ab\u30fc\u30c8\u30fb \u30d4\u30f3\u30af",
            "\u304a\u3057\u3068\u3084\u304b\u30ea\u30dc\u30f3\u30b9\u30ab\u30fc\u30c8\u30fb\u30a4\u30a8\u30ed\u30fc",
            "\u30bc\u30d6\u30e9\u67c4\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30c1\u30a7\u30c3\u30af\u30b0\u30ea\u30fc\u30f3\u30c8\u30fc\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30c1\u30a7\u30c3\u30af\u30ec\u30c3\u30c9\u30c8\u30fc\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30c1\u30a7\u30c3\u30af\u30d6\u30eb\u30fc\u30c8\u30fc\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30c1\u30a7\u30c3\u30af\u30e2\u30ce\u30c8\u30fc\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30c3\u30af\u30ab\u30e9\u30fc\u9752\u30b9\u30ab\u30fc\u30c8",
            "\u30ef\u30f3\u30dd\u30a4\u30f3\u30c8\u4ed8\u304d\u30ed\u30fc\u30eb\u30a2\u30c3\u30d7\u30c7\u30cb\u30e0",
            "\u3086\u3081\u304b\u308f\u98a8\u5236\u670d\u30b3\u30fc\u30c6\u3099\u30fb\u3084\u3093\u3067\u308b\u7cfb\u30ab\u30e9\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u304a\u3059\u307e\u3057\u30b9\u30af\u30fc\u30eb\u30b3\u30fc\u30c6\u3099\u30fb\u30b7\u30c3\u30af\u30ab\u30e9\u30fc\u8d64\u30b9\u30ab\u30fc\u30c8",
            "\u30e2\u30ce\u30af\u30ed\u306b\u3083\u3093\u3053\u30b3\u30fc\u30c6\u3099\u30fb\u30b0\u30ec\u30fc\u30ab\u30e2\u30d5\u30e9\u30b9\u30ab\u30fc\u30c8",
            "\u30de\u30ea\u30f3\u30ab\u30e9\u30fc\u98a8\u30ed\u30f3\u30b0\u30b9\u30ab\u30fc\u30c8",
            "\u30a4\u30a8\u30ed\u30fc\u30c1\u30a7\u30c3\u30af\u30b9\u30af\u30fc\u30eb\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u8d64\u30c1\u30a7\u30c3\u30af\u30b9\u30af\u30fc\u30eb\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30e2\u30d5\u30e9\u67c4\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30e9\u30a4\u30c8\u30d6\u30e9\u30a6\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u5439\u594f\u697d\u90e8\u54e1\u30b3\u30fc\u30c6\u3099\u30fb\u30d6\u30e9\u30a6\u30f3\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30aa\u30c8\u30ca\u9ed2\u30ab\u30b8\u30e5\u30a2\u30eb\u30b3\u30fc\u30c7\u30fb\u30e2\u30ce\u30af\u30ed\u30b9\u30c8\u30e9\u30a4\u30d7\u30d1\u30f3\u30c4",
            "\u3086\u3063\u305f\u308a\u30ac\u30a6\u30c1\u30e7\u30d1\u30f3\u30c4\u30fb\u30c7\u30cb\u30e0",
            "\u30d1\u30b9\u30c6\u30eb\u5236\u670d\u30fb\u30d1\u30b9\u30c6\u30eb\u30ab\u30e9\u30fc\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30aa\u30c8\u30ca\u30ab\u30b7\u3099\u30e5\u30a2\u30eb\u30b7\u30f3\u30d5\u309a\u30eb\u30fb\u30e2\u30ce\u30af\u30ed\u30b9\u30c8\u30e9\u30a4\u30d7\u30d1\u30f3\u30c4",
            "\u30ed\u30fc\u30eb\u30a2\u30c3\u30d7\u30c0\u30e1\u30fc\u30b8\u30d1\u30f3\u30c4\u30fb\u30b0\u30ea\u30fc\u30f3",
            "\u30ed\u30fc\u30eb\u30a2\u30c3\u30d7\u30c0\u30e1\u30fc\u30b8\u30d1\u30f3\u30c4\u30fb\u30d6\u30eb\u30fc",
            "\u5927\u4eba\u98a8\u30b3\u30fc\u30c7\u30fb\u30ed\u30fc\u30eb\u30a2\u30c3\u30d7\u30c0\u30e1\u30fc\u30b8\u30c7\u30cb\u30e0",
            "\u30ef\u30f3\u30c8\u30fc\u30f3\u30b3\u30fc\u30c7\u30fb\u767d\u30b9\u30ab\u30fc\u30c8",
            "\u843d\u3061\u7740\u304d\u30ab\u30b8\u30e5\u30a2\u30eb\u30b3\u30fc\u30c7\u30fb\u30c0\u30e1\u30fc\u30b8\u30c7\u30cb\u30e0",
            "\u30ab\u30e9\u30d5\u30eb\u30c9\u30c3\u30c8\u30b3\u30fc\u30c7\u30fb\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30dc\u30fc\u30a4\u30c3\u30b7\u30e5\u30b3\u30fc\u30c7\u30fb\u30dc\u30fc\u30a4\u30ba\u30c7\u30cb\u30e0",
            "\u8896\u900f\u3051\u30b9\u30dd\u30fc\u30c6\u30a3\u30c9\u30c3\u30c8\u30d1\u30f3\u30c4",
            "\u30b7\u30f3\u30d7\u30eb\u30ce\u30fc\u30b9\u30ea\u30fc\u30d6\u30c7\u30cb\u30e0\u30d5\u30ec\u30a2\u30b9\u30ab\u30fc\u30c8",
            "\u82b1\u67c4\u30b5\u30eb\u30a8\u30eb\u30d1\u30f3\u30c4",
            "\u30ac\u30fc\u30ea\u30a3\u30ab\u30e9\u30fc\u5236\u670d\u30b3\u30fc\u30c7\u30fb\u30db\u30ef\u30a4\u30c8\u30e9\u30a4\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30b9\u30dd\u30fc\u30c6\u30a3\u30b3\u30fc\u30c7\u30fb\u30ec\u30fc\u30b9\u67c4\u30c7\u30cb\u30e0\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d0\u30ab\u30f3\u30b9\u98a8\u30e4\u30b7\u306e\u6728\u67c4\u30b0\u30ea\u30fc\u30f3\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d0\u30ab\u30f3\u30b9\u98a8\u30e4\u30b7\u306e\u6728\u67c4\u30d6\u30eb\u30fc\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30b5\u30b9\u30da\u30f3\u30c0\u30fc\u4ed8\u304d\u30b8\u30fc\u30f3\u30ba\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30b9\u30de\u30a4\u30eb\u30ab\u30b8\u30e5\u30a2\u30eb\u30ab\u30d7\u30ea\u30d1\u30f3\u30c4",
            "\u7de8\u307f\u30ea\u30dc\u30f3\u30db\u30ef\u30a4\u30c8\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30ec\u30e2\u30f3\u67c4\u9ed2\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d1\u30b9\u30c6\u30eb\u30ab\u30e9\u30d5\u30eb\u30d1\u30f3\u30c4",
            "\u30d1\u30b9\u30c6\u30eb\u30a4\u30a8\u30ed\u30fc\u30ed\u30f3\u30b0\u30b9\u30ab\u30fc\u30c8",
            "\u30e9\u30d9\u30f3\u30c0\u30fc\u30ab\u30e9\u30fc\u30ed\u30f3\u30b0\u30b9\u30ab\u30fc\u30c8",
            "\u30a2\u30cb\u30de\u30eb\u67c4\u30d9\u30eb\u30c8\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d6\u30ed\u30c3\u30b5\u30e0\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u9577\u8896\u30bb\u30fc\u30e9\u30fc\u30b3\u30fc\u30c7\u30fb\u7d3a\u30bb\u30fc\u30e9\u30fc\u30ed\u30f3\u30b0\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30c7\u30cb\u30e0\u30fb\u30b9\u30af\u30fc\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30e2\u30ce\u30af\u30ed\u304a\u5b22\u69d8\u30b3\u30fc\u30c7\u30fb\u9ed2\u8272\u30d0\u30eb\u30fc\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30b8\u30e3\u30f3\u30f3\u30b0\u30eb\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u7121\u5730\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4\u30fb\u30d4\u30f3\u30af",
            "\u30dc\u30d8\u30df\u30a2\u30f3\u30d5\u30ea\u30f3\u30b8\u30b3\u30fc\u30c7\u30fb\u7121\u5730\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4\u30fb\u30a4\u30a8\u30ed\u30fc",
            "\u6f2b\u753b\u5bb6\u30b3\u30fc\u30c7\u30fb\u304a\u3068\u306a\u3057\u3081\u30b7\u30f3\u30d7\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u5bb6\u5ead\u79d1\u30b3\u30fc\u30c7\u30fb\u7d3a\u8272\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u79d1\u5b66\u90e8\u30b3\u30fc\u30c7\u30fb\u30b1\u30df\u30ab\u30eb\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30dc\u30c1\u30e3\u30d1\u30f3\u30c4\u30fb\u30db\u30ef\u30a4\u30c8",
            "\u30ab\u30dc\u30c1\u30e3\u30d1\u30f3\u30c4\u30fb\u30d4\u30f3\u30af",
            "\u304a\u3068\u306a\u3057\u3081\u30ac\u30fc\u30eb\u30b3\u30fc\u30c7\u30fb\u30b7\u30f3\u30d7\u30eb\u30b9\u30ad\u30cb\u30fc",
            "\u30d1\u30b9\u30c6\u30eb\u30a4\u30a8\u30ed\u30fc\u30b9\u30ad\u30cb\u30fc\u30d1\u30f3\u30c4",
            "\u30d1\u30b9\u30c6\u30eb\u30d1\u30fc\u30d7\u30eb\u30b9\u30ad\u30cb\u30fc\u30d1\u30f3\u30c4",
            "\u30d9\u30eb\u30c8\u4ed8\u304d\u30d1\u30b9\u30c6\u30eb\u30d4\u30f3\u30af\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d9\u30eb\u30c8\u4ed8\u304d\u30d1\u30b9\u30c6\u30eb\u30b0\u30ea\u30fc\u30f3\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30ec\u30e2\u30f3\u30a4\u30a8\u30ed\u30fc\u30b7\u30f3\u30d7\u30eb\u30df\u30cb\u30b9\u30ab",
            "\u30d1\u30b9\u30c6\u30eb\u30d6\u30eb\u30fc\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30db\u30ef\u30a4\u30c8\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u6625\u30c7\u30cb\u30e0\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u30d4\u30f3\u30af\u30d6\u30e9\u30a6\u30f3\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u653e\u8ab2\u5f8c\u30ad\u3099\u30e3\u30eb\u5236\u670d\u30b3\u30fc\u30c6\u3099\u30fb\u30e2\u30ce\u30af\u30ed\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30e2\u30d5\u30e9\u30ab\u30b7\u3099\u30e5\u30a2\u30eb\u30b3\u30fc\u30c6\u3099\u30fb\u30b7\u30f3\u30d7\u30eb\u30d1\u30f3\u30c4",
            "\u3086\u308b\u30cb\u30c3\u30c8\u30ad\u3099\u30e3\u30eb\u30b3\u30fc\u30c6\u3099\u30fb\u30c0\u30e1\u30fc\u30b8\u30c7\u30cb\u30e0\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u8d64\u30ab\u30fc\u30c7\u52c9\u5f37\u30fb\u7d3a\u30c1\u30a7\u30c3\u30af\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30e9\u30d5\u30bb\u30fc\u30bf\u30fc\u5236\u670d\u30fb\u6d3e\u624b\u3081\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u304a\u4ed5\u4e8b\u30b3\u30fc\u30c6\u3099\u4fdd\u80b2\u58eb\u30fb\u30b8\u30e3\u30fc\u30b8\u30dc\u30c8\u30e0\u30b9",
            "\u30aa\u30c8\u30ca\u82b1\u67c4\u30ef\u30f3\u30d2\u309a\u30b3\u30fc\u30c6\u3099\u30fb\u82b1\u67c4\u30ed\u30f3\u30b0\u30b9\u30ab\u30fc\u30c8",
            "\u6bb5\u30d5\u30ea\u30eb\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8\u30fb\u30d1\u30fc\u30d7\u30eb",
            "\u30b7\u30f3\u30d7\u30eb\u30dc\u30c8\u30e0\u30b9\u30fb\u30db\u30ef\u30a4\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30dc\u30c8\u30e0\u30b9\u30fb\u30d6\u30eb\u30fc",
            "\u5236\u670d\u98a8\u30b3\u30fc\u30c7\u30fb\u9752\u8272\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30eb\u30fc\u30ba\u30ae\u30e3\u30eb\u30fb\u5236\u670d\u30b9\u30ab\u30fc\u30c8",
            "\u6625\u8272\u30ac\u30fc\u30ea\u30fc\u30fb\u30cb\u30fc\u30cf\u30a4\u3064\u304d\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30d5\u30ea\u30eb\u30df\u30cb\u30c9\u30ec\u30b9\u98a8\u30b3\u30fc\u30c7\u30fb\u9ed2\u8272\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30b7\u30e3\u30c4\u30b3\u30fc\u30c7\u30fb\u30b9\u30ad\u30cb\u30fc\u30d1\u30f3\u30c4",
            "\u5927\u4eba\u3052\u30ab\u30b8\u30e5\u30a2\u30ebT\u30b7\u30e3\u30c4\u30b3\u30fc\u30c7\u30fb\u9ed2\u8272\u30ec\u30ae\u30f3\u30b9",
            "\u30b3\u30f3\u30b5\u30d0\u7cfb\u304a\u5b22\u69d8\u30b3\u30fc\u30c7\u30fb\u30ec\u30fc\u30b9\u3064\u304d\u30b9\u30ab\u30fc\u30c8",
            "\u5927\u4eba\u6625\u30ab\u30b8\u30e5\u30a2\u30eb\u30b3\u30fc\u30c7\u30fb\u30b9\u30ad\u30cb\u30fc",
            "\u30ea\u30dc\u30f3\u30d6\u30ec\u30b6\u30fc\u5236\u670d\u30fb\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30b4\u30b7\u30c3\u30af\u30d1\u30f3\u30af\u30fb\u8d64\u30b9\u30ab\u30fc\u30c8",
            "\u30b9\u30dd\u30fc\u30c6\u30a3\u30ab\u30b8\u30e5\u30a2\u30eb\u30fb\u9ed2\u8272\u30c7\u30cb\u30e0",
            "\u30d1\u30b9\u30c6\u30eb\u30d6\u30eb\u30fc\u30ed\u30fc\u30eb\u30a2\u30c3\u30d7\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u6625\u8272\u30b9\u30dd\u30fc\u30c6\u30a3\u30b3\u30fc\u30c7\u30fb\u3055\u308f\u3084\u304b\u30e9\u30a4\u30f3\u5165\u308a\u767d\u30b9\u30ab\u30fc\u30c8",
            "\u30d5\u30e9\u30ef\u30fc\u30a8\u30b9\u30cb\u30c3\u30af\u30b3\u30fc\u30c7\u30fb\u30d6\u30e9\u30c3\u30af\u30c7\u30cb\u30e0\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30bb\u30fc\u30e9\u30fc\u30ab\u30fc\u30c6\u3099\u5236\u670d\u30b3\u30fc\u30c6\u3099\u30fb\u30e9\u30a4\u30f3\u5165\u308a\u7d3a\u30b9\u30ab\u30fc\u30c8",
            "\u30af\u30e9\u30b7\u30ab\u30eb\u5236\u670d\u30fb\u30d6\u30e9\u30a6\u30f3\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30c7\u30cb\u30e0\u30b9\u30ab\u30fc\u30c8",
            "\u30b3\u30f3\u30b5\u30d0\u7cfb\u30fb\u30d4\u30f3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u3075\u3093\u308f\u308a\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4\u30fb\u767d",
            "\u534a\u8896\u30bb\u30fc\u30e9\u30fc\u30fb\u30b5\u30de\u30fc\u30bb\u30fc\u30e9\u30fc\u30df\u30cb\u30b9\u30ab",
            "\u8f9b\u53e3\u30e9\u30a4\u30c0\u30fc\u30b9\u30fb\u30c0\u30e1\u30fc\u30b8\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u30b7\u30c3\u30af\u306a\u5236\u670d\u30fb\u30b7\u30f3\u30d7\u30eb\u30df\u30cb\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u5236\u670d\u30fb\u30b7\u30f3\u30d7\u30eb\u30bb\u30fc\u30e9\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u30b7\u30f3\u30d7\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30d4\u30f3\u30af",
            "\u30b7\u30f3\u30d7\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30d9\u30fc\u30b8\u30e5",
            "\u30d5\u30ea\u30eb\u30b7\u30f3\u30d7\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30ec\u30c3\u30c9",
            "\u30d5\u30ea\u30eb\u30b7\u30f3\u30d7\u30eb\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30eb\u30fc",
            "\u30d4\u30f3\u30af\u30c7\u30fc\u30c8\u30fb\u30d4\u30f3\u30af\u30d5\u30ea\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u30de\u30ea\u30f3\u30b9\u30dd\u30fc\u30c4\u30fb\u9752\u8272\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30df\u30ea\u30bf\u30ea\u30fc\u30b3\u30fc\u30c7\u30fb\u767d\u8272\u30ec\u30fc\u30b9\u30b9\u30ab\u30fc\u30c8",
            "\u3075\u3093\u308f\u308a\u30cb\u30c3\u30c8\u30ab\u30b8\u30e5\u30a2\u30eb\u30fb\u30d6\u30e9\u30a6\u30f3\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30cd\u30af\u30bf\u30a4\u30d6\u30ec\u30b6\u30fc\u5236\u670d\u30fb\u30d7\u30ea\u30fc\u30c4\u30b9\u30ab\u30fc\u30c8",
            "\u5236\u670d\u98a8\u30b3\u30fc\u30c7\u30fb\u9ed2\u30b9\u30ab\u30fc\u30c8",
            "\u30d1\u30b9\u30c6\u30eb\u30ab\u30e9\u30fc\u30b9\u30dd\u30fc\u30c6\u30a3\u30b3\u30fc\u30c7\u30fb\u30d4\u30f3\u30af\u30e9\u30a4\u30f3\u30b0\u30ec\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u304a\u59c9\u3055\u3093\u30c6\u3099\u30cb\u30e0\u30b3\u30fc\u30c6\u3099\u30fb\u3055\u308f\u3084\u304b\u30c7\u30cb\u30e0\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30dc\u30fc\u30c0\u30fc\u5165\u308a\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30eb\u30fc",
            "\u30d4\u30f3\u30af\u30c7\u30fc\u30c8\u30fb\u9ed2\u8272\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u6625\u30d4\u30af\u30cb\u30c3\u30af\u30fb\u30ae\u30f3\u30ac\u30e0\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u30e2\u30c6\u5236\u670d\u30fb\u7d3a\u8272\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8",
            "\u304a\u306d\u3048\u7cfb\u30ab\u30b8\u30e5\u30a2\u30eb\u30b3\u30fc\u30c7\u30fb\u30c7\u30cb\u30e0\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30aa\u30d5\u30a3\u30b7\u30e3\u30eb\u30b3\u30fc\u30c7\u30fb\u3075\u3093\u308f\u308a\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30e9\u30d5\u30ebT\u30b7\u30e3\u30c4\u30b3\u30fc\u30c7\u30fb\u9ed2\u8272\u30ec\u30ae\u30f3\u30b9",
            "\u30b9\u30af\u30fc\u30eb\u30d1\u30f3\u30af\u30fb\u30d7\u30ea\u30fc\u30c4\u30b9\u30ab\u30fc\u30c8",
            "\u30de\u30ea\u30f3\u98a8\u30bb\u30fc\u30e9\u30fc\u670d\u30fb\u9752\u8272\u30b9\u30ab\u30fc\u30c8",
            "\u30d1\u30f3\u30af\u98a8\u30d6\u30ec\u30b6\u30fc\u5236\u670d\u30fb\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30dc\u30fc\u30a4\u30c3\u30b7\u30e5\u30fb\u9ed2\u30bf\u30a4\u30c4\u3064\u304d\u30b7\u30f3\u30d7\u30eb\u30d1\u30f3\u30c4",
            "\u30ae\u30f3\u30ac\u30e0\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8\u30fb\u30d6\u30e9\u30c3\u30af",
            "\u30ae\u30f3\u30ac\u30e0\u30c1\u30a7\u30c3\u30af\u30b9\u30ab\u30fc\u30c8\u30fb\u30e9\u30a4\u30c8\u30d6\u30eb\u30fc",
            "\u30b8\u30e3\u30fc\u30b8\u98a8\u30b3\u30fc\u30c7\u30fb\u7d3a\u30bd\u30c3\u30af\u30b9\u3064\u304d\u30b7\u30e7\u30fc\u30c8\u30d1\u30f3\u30c4",
            "\u539f\u5bbf\u7cfb\u304a\u5b22\u69d8\u30b3\u30fc\u30c7\u30fb\u30ab\u30e9\u30d5\u30eb\u30b9\u30ab\u30fc\u30c8",
            "\u9577\u8896\u30bb\u30fc\u30bf\u30fc\u5236\u670d\u30fb\u9752\u8272\u30b9\u30ab\u30fc\u30c8",
            "\u30dc\u30fc\u30a4\u30c3\u30b7\u30e5\u30ab\u30b8\u30e5\u30a2\u30eb\u30fb\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u304a\u3088\u3070\u308c\u30b3\u30fc\u30c7\u30fb\u767d\u8272\u30b9\u30ab\u30fc\u30c8",
            "\u30d9\u30fc\u30b7\u30c3\u30af\u5236\u670d\u30fb\u30b9\u30ab\u30fc\u30c8",
            "\u30ab\u30b8\u30e5\u30a2\u30eb\u30b4\u30b7\u30c3\u30af\u30d1\u30f3\u30af\u30fb\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30db\u30ef\u30a4\u30c8\u30d5\u30ec\u30a2\u30b9\u30ab\u30fc\u30c8",
            "\u30b8\u30e3\u30fc\u30b8\u30b3\u30fc\u30c7\u30fb\u7d3a\u8272\u30cf\u30fc\u30d5\u30d1\u30f3\u30c4",
            "\u6625\u8272\u3055\u308f\u3084\u304b\u30fb\u7d3a\u8272\u30b7\u30e7\u30fc\u30d1\u30f3",
            "\u30aa\u30d5\u30a3\u30b9\u30ab\u30b8\u30e5\u30a2\u30eb\u30fb\u767d\u8272\u30b9\u30ab\u30fc\u30c8",
            "\u30d9\u30fc\u30b7\u30c3\u30af\u5236\u670d\u30fb\u30b0\u30ec\u30fc\u30b9\u30ab\u30fc\u30c8",
            "\u5236\u670d\u98a8\u30b3\u30fc\u30c7\u30fb\u7d3a\u30b9\u30ab\u30fc\u30c8"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.unicode_textview);
        final ListView listview = (ListView) findViewById(R.id.listview);

        TextView txt = (TextView) findViewById(R.id.text1);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/dejavusans.ttf");
        txt.setTypeface(font);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
//                final String item = (String) parent.getItemAtPosition(position);

            }

        });

    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
