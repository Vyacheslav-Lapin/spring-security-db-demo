package ru.vlapin.demo.springsecuritydbdemo.common;

import io.vavr.Function2;
import io.vavr.Function3;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class FunctionUtils {

  public <V1, V2, R, T> Function2<T, V2, R> compose1(@NotNull Function2<V1, V2, R> self,
                                                     @NotNull Function<T, V1> before) {
    return (t, v2) -> self.apply(before.apply(t), v2);
  }

  public <V1, V2, R, T> Function2<V1, T, R> compose2(@NotNull Function2<V1, V2, R> self,
                                                     @NotNull Function<T, V2> before) {
    return (v1, t) -> self.apply(v1, before.apply(t));
  }

  public <V1, V2, V3, R, T> Function3<T, V2, V3, R> compose1(@NotNull Function3<V1, V2, V3, R> self,
                                                             @NotNull Function<T, V1> before) {
    return (t, v2, v3) -> self.apply(before.apply(t), v2, v3);
  }

  public <V1, V2, V3, R, T> Function3<V1, T, V3, R> compose2(@NotNull Function3<V1, V2, V3, R> self,
                                                             @NotNull Function<T, V2> before) {
    return (v1, t, v3) -> self.apply(v1, before.apply(t), v3);
  }

  public <V1, V2, V3, R, T> Function3<V1, V2, T, R> compose3(@NotNull Function3<V1, V2, V3, R> self,
                                                             @NotNull Function<T, V3> before) {
    return (v1, v2, t) -> self.apply(v1, v2, before.apply(t));
  }
}
