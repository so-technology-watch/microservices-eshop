FROM lechelong/eshop-basex64
RUN apk --update add python3-dev build-base linux-headers pcre-dev

WORKDIR /app
ADD . /app

RUN pip3 install -r requirements.txt

EXPOSE 9091
CMD ["python3", "app.py"]
