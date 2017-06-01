FROM lechelong/eshop-basex64
RUN apk --update add python3-dev build-base linux-headers pcre-dev

COPY ./data ./data
WORKDIR data
RUN ls
RUN pip3 install -r requirements.txt

EXPOSE 9091
CMD ["python3", "app.py"]
