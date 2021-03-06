package mapred.hashtagsim;

import java.io.IOException;

import mapred.util.Tokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JobMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] words = Tokenizer.tokenize(line);

		/*
		 * Iterate all words, find out hastag #job, then iterate all other non-hashtag 
		 * words and map out.
		 */
		for (String word : words) 
			if (word.equals("#job")) 
				for (String word2 : words)
					if (word2.startsWith("#")==false)
						context.write(new Text("#job"), new Text(word2));
		
		
	}
}
