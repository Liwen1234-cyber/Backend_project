package cn.itheima;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .apiKey("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")
                .modelName("deepseek/deepseek-r1:free")
                .logRequests(true)
                .logResponses(true)
                .build();

        String result = model.chat("吴彦祖帅吗");
        System.out.println(result);


    }
}
