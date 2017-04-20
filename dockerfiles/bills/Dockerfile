FROM hypriot/rpi-alpine-scratch
RUN apk --update add python3 py-pip

WORKDIR /app
ADD . /app

RUN pip3 install -r requirements.txt

EXPOSE 9090
ENV CONSUL_CLIENT 10.226.159.191
CMD ["python3", "app.py"]
