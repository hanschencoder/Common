/*
 * Copyright 2016 Hans Chen
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
package site.hanschen.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareUtils {

	public static void shareSingleFile(@NonNull Context context, @NonNull File file, String title) {

		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));

		String extension = FileUtils.getFileExtension(file.getAbsolutePath());
		shareIntent.setType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension));

		context.startActivity(Intent.createChooser(shareIntent, title));
	}

	public static void shareMultipleFiles(@NonNull Context context, @NonNull List<File> files, String title) {

		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
		ArrayList<Uri> uris = new ArrayList<>();
		for (File file : files) {
			uris.add(Uri.fromFile(file));
		}
		shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

		String extension = FileUtils.getFileExtension(files.get(0).getAbsolutePath());
		shareIntent.setType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension));

		context.startActivity(Intent.createChooser(shareIntent, title));
	}
}
