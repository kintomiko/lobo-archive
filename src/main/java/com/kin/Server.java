package com.kin;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by link on 10/05/17.
 */
public class Server extends AbstractVerticle {

    private Game game = new Game();

    @Override
    public void start() {
        HttpServerOptions options = new HttpServerOptions()
                .setUseAlpn(true)
                .setSsl(true)
                .setKeyStoreOptions(new JksOptions()
                        .setPath("/home/link/git/lobo/clientkeystore")
                        .setPassword("123123"))
                .setJdkSslEngineOptions(new JdkSSLEngineOptions());
        vertx.createHttpServer(options)
            .requestHandler(req -> {
                HttpServerResponse response = req.response();
                response.putHeader("content-type", "text/plain")
                        .end(String.valueOf(System.nanoTime()));
            })
            .websocketHandler(ws -> {
                if (ws.uri().startsWith("/state")) {
                    ws.handler((data) -> {
                        BufferedImage buffImg =
                                new BufferedImage(game.getCurrentState().length, game.getCurrentState()[0].length, BufferedImage.TYPE_INT_ARGB);
                        buffImg.setRGB();
                        ws.writeTextMessage(
                                new JsonArray(Arrays.asList(game.getCurrentState())).encode());
                    });
                }
                if (ws.uri().startsWith("/steps")) {
                    ws.handler((data) -> {
                        JsonObject reqJson = data.toJsonObject();
                        int fromStep = reqJson.getInteger("fromStep");
                        loopPushSteps(fromStep, ws);
                    });
                }
                if (ws.uri().startsWith("/change")) {
                    ws.handler((data) -> {
                        JsonObject reqJson = data.toJsonObject();
                        game.change(reqJson.getInteger("x"),
                                reqJson.getInteger("y"),
                                reqJson.getInteger("value"));
                    });
                }

            }).listen(8881);
    }

    private void loopPushSteps(int fromStep, ServerWebSocket ws) {
        List<Change> steps = game.pullHistory(fromStep);
        int nextStep = fromStep + steps.size();
        ws.writeTextMessage(
                new JsonArray(steps).encode());
        vertx.setTimer(1000, (e) -> loopPushSteps(nextStep, ws));
    }

}
